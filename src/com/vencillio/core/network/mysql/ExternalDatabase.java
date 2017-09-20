package com.vencillio.core.network.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExternalDatabase implements Runnable {


	private class DatabaseConnection {


		private Connection connection;


		private long lastPing = System.currentTimeMillis();


		private long lastQuery;


		private long lastReconnect = System.currentTimeMillis() + new Random().nextInt(120000); // Initial
		// Time
		// setting,
		// because otherwise
		// they close/reopen all
		// at the same time


		private final String password;

		private State state = State.INACTIVE;


		private final String url;


		private final String username;

		
		private DatabaseConnection(String username, String password, String url) {
			this.username = username;
			this.password = password;
			this.url = "jdbc:mysql://" + url;
		}

		
		private void close() {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Error closing database connection", e);
				}

				setState(State.INACTIVE);
			}
		}

		private boolean connect() {
			setState(State.INACTIVE);

			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Error connecting to MySQL database (" + url + ") !", e);
				return false;
			}

			return true;
		}


		public Connection getConnection() {
			return connection;
		}


		public State getState() {
			synchronized (this) {
				return state;
			}
		}

	
		private boolean ping() {
			lastPing = System.currentTimeMillis();

			if ((connection != null) && ((System.currentTimeMillis() - lastReconnect) > 5000)) {
				try {
					connection.prepareStatement("SELECT 1").executeQuery();
				} catch (SQLException e) {
					return false;
				}

				return true;
			}

			return false;
		}

	
		public void setState(State state) {
			synchronized (this) {
				this.state = state;
			}
		}

	}


	private class InteralQuery implements Callable<ResultSet> {

	
		private final String query;

		private InteralQuery(String query) {
			this.query = query;
		}


		@Override
		public ResultSet call() {

			PreparedStatement statement = null;
			boolean isUpdating = !query.toLowerCase().startsWith("select");
			DatabaseConnection pooledConnection = getPooledConnection();

			if (pooledConnection == null) {
				if (isUpdating) {
					addFailedQuery(this);
				}

				System.out.println("Unexpected: pooled connection returned null..");

				return null;
			}

			pooledConnection.setState(State.BUSY);
			pooledConnection.lastQuery = System.currentTimeMillis();

			try {
				statement = pooledConnection.getConnection().prepareStatement(query);

				if (isUpdating) {
					statement.executeUpdate();
				} else {
					return statement.executeQuery();
				}
			} catch (SQLException e) {
				if (isUpdating) {
					addFailedQuery(this);
				}
				e.printStackTrace();
			} finally {
				if ((statement != null) && isUpdating) {
					try {
						statement.close();
					} catch (SQLException ex) {
						logger.log(Level.WARNING, "Error closing statement", ex);
					}
				}

				pooledConnection.setState(State.IDLE);
			}

			return null;
		}

		@Override
		public String toString() {
			return query;
		}

	}


	private static enum State {

		BUSY,
		IDLE,
		INACTIVE

	}


	private static Logger logger = Logger.getLogger(ExternalDatabase.class.getName());

	private final Queue<InteralQuery> failedQueries = new LinkedList<>();


	private boolean isRunning = true;


	private long pingInterval = 60000;


	private final DatabaseConnection[] pool;


	private final ExecutorService workService;

	
	public ExternalDatabase(String username, String password, String url, int poolSize) {
		pool = new DatabaseConnection[poolSize];
		workService = Executors.newFixedThreadPool(pool.length);

		for (int i = 0; i < pool.length; i++) {
			pool[i] = new DatabaseConnection(username, password, url);
		}

		new Thread(this, "DatabaseConnection").start();
	}


	private void addFailedQuery(InteralQuery query) {
		synchronized (this) {
			failedQueries.add(query);
			System.out.println(String.format("SQL query failed [failCount: %d]: %s", failedQueries.size(), query.query));
		}
	}


	public ResultSet executeQuery(String query) {
		ResultSet result = null;

		if (!query.toLowerCase().startsWith("select")) {
			workService.submit(new InteralQuery(query));
		} else {
			Future<?> future = workService.submit(new InteralQuery(query));

			try {
				result = (ResultSet) future.get();
			} catch (Exception e) {
				logger.log(Level.WARNING, "Error executing query!", e);
			}
		}

		return result;
	}


	public DatabaseConnection getPooledConnection() {
		for (DatabaseConnection connection : pool) {
			if (connection.getState().equals(State.IDLE)) {
				return connection;
			}
		}

		return null;
	}


	public void initialise() {
		int connectionCount = 0;

		for (DatabaseConnection poolConnection : pool) {
			if (!poolConnection.connect()) {
				continue;
			}

			connectionCount++;
			poolConnection.setState(State.IDLE);
		}

		System.out.println("Succesfully opened " + connectionCount + " database connection.");
	}

	@Override
	public void run() {
		while (isRunning) {

			// Sleep a little..
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				continue;
			}

			// Ping the connections in the pool.
			for (DatabaseConnection connection : pool) {
				if ((System.currentTimeMillis() - connection.lastPing) <= pingInterval) {
					continue;
				}

				synchronized (connection) {
					if (connection.getState() == State.IDLE) {
						if (((System.currentTimeMillis() - connection.lastReconnect) > 120000) && ((System.currentTimeMillis() - connection.lastQuery) > 1000)) {
							connection.close();
							connection.lastReconnect = System.currentTimeMillis();
						}
					}
				}

				if (!connection.ping()) {
					boolean reconnected = connection.connect();

					if (reconnected) {
						connection.setState(State.IDLE);
					}
				}
			}

			// Execute queries that previously failed.
			synchronized (this) {
				if (!failedQueries.isEmpty()) {
					InteralQuery query;

					while ((query = failedQueries.poll()) != null) {
						workService.submit(query);
					}
				}
			}
		}

	}


	public void setPingInterval(int pingInterval) {
		this.pingInterval = pingInterval * 60000;
	}


	public void shutdown() {
		isRunning = false;
		failedQueries.clear();

		if (workService != null) {
			workService.shutdown();
		}

		for (DatabaseConnection connection : pool) {
			connection.close();
		}
	}

}
