package com.vencillio.core.network.mysql;

import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vencillio.VencillioConstants;
import com.vencillio.core.util.Utility;
import com.vencillio.rs2.entity.World;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;


public final class VoteUpdater {

	/*
	private static ExternalDatabase database = null;


	private static ExecutorService executorService = null;


	private static boolean prepared = false;


	public static void prepare() {
		database = new ExternalDatabase("vdaniel_ipb", "PBDQC7UqKdDa", "108.167.182.37/vdaniel_ipb", 3);
		database.initialise();
		executorService = Executors.newSingleThreadExecutor();
		prepared = true;
	}


	public static void shutdown() {
		database.shutdown();
		executorService.shutdown();
	}
	
	
	public static void update(Player player) {
		if (!prepared) {
			throw new IllegalStateException("unprepared");
		}

		if ((System.currentTimeMillis() - player.getLastRequestedLookup()) < 30000) {
			player.send(new SendMessage("Sorry, you can only check your votes once per 30 seconds."));
			return;
		}

		player.setLastRequestedLookup(System.currentTimeMillis());

		executorService.submit(() -> {
			try (ResultSet resultSet = database.executeQuery("SELECT `incentive`, `ready` FROM `mv_rewards` WHERE `user` = '" + Utility.formatPlayerName(player.getUsername().trim().toLowerCase()) + "'")) {
				boolean go = false;

				while (resultSet.next()) {
					final boolean ready = resultSet.getInt("ready") == 1;
					final int incentive = resultSet.getInt("incentive");
					
					if (!ready) {
						player.send(new SendMessage("You must complete the voting process to claim your reward."));
						return;
					}
					mo
						handleRewards(player, new Item(386, 100), "100 Sharks");					
					} else if (incentive == 4) {
						handleRewards(player, new Item(11738, 1), "1 Herblore box");
					} else if (incentive == 5) {
						handleRewards(player, new Item(6199, 1), "1 Mystery Box");
					}
					
					go = true;
				}

				if (!go) {
					player.send(new SendMessage("You don't have any votes to be claimed."));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});on
	}
	

	private static void handleRewards(Player player, Item item, String reward) {
		VencillioConstants.LAST_VOTER = player.getUsername();
		VencillioConstants.CURRENT_VOTES++;
		player.setVotePoints(player.getVotePoints() + 1);
		player.send(new SendMessage("Thank you for voting, " + Utility.formatPlayerName(player.getUsername()) + "!"));
		World.sendGlobalMessage("<img=8> @red@" + Utility.formatPlayerName(player.getUsername()) + " has just voted for " + reward + "!");
		database.executeQuery("DELETE FROM `mv_rewards` WHERE `user` = '" + Utility.formatPlayerName(player.getUsername().trim().toLowerCase()) + "'");
		if (player.ironPlayer()) {
			player.send(new SendMessage("As you are using an Iron account you only recieved vote points."));
			return;
		}
		player.getInventory().addOrCreateGroundItem(item);
	}*/
}