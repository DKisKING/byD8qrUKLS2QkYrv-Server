package com.vencillio.rs2.content;

import java.util.ArrayList;
//import java.util.logging.Logger;

import com.vencillio.core.task.Task;
import com.vencillio.core.task.TaskQueue;
import com.vencillio.core.util.Utility;
import com.vencillio.rs2.entity.World;

/**
 * Handles the global messages
 * @author Daniel
 *
 */
public class GlobalMessages {
	
	/**
	 * The logger for the class
	 */
	//private static Logger logger = Logger.getLogger(GlobalMessages.class.getSimpleName());

	/**
	 * The news color text
	 */
	private static String newsColor = "<col=cc00cc>";

	/**
	 * The news icon
	 */
	private static String iconNumber = "<img=8>";
	
	/**
	 * Holds all the announcements in a arraylist
	 */
	public final static ArrayList<String> announcements = new ArrayList<String>();

	/**
	 * The random messages that news will send
	 */
	public static final String[] ANNOUNCEMENTS = { 
			"Be sure to set a unique password for your account. Use ::changepassword any time!",
			"If you find a bug or issue with the game, inform an Admin immediately!",
			"Vote on a daily basis to recieve great rewards whilst helping the server to grow!",
			"Sell and Buy items 24/7 at the Player-Owned Shops! Find the hub at home bank!",
			"Click the world map or type ::teleport to access the teleportation menu!",
			"Want to see a list of everyone online? Use ::players!",
			"Visit our website for all of the latest Tyras updates and information!",
			"Looking to make some quick cash? Be sure to always answer trivia questions!",
			
	};
	
	/**
	 * Declares all the announcements
	 */
	public static void declare() {
		for (int i = 0; i < ANNOUNCEMENTS.length; i++) {
			announcements.add(ANNOUNCEMENTS[i]);
		}
		//logger.info(Utility.format(announcements.size()) + " Announcements have been loaded successfully.");
	}

	/**
	 * Initializes the task
	 */
	public static void initialize() {
		TaskQueue.queue(new Task(250, false) {
			@Override
			public void execute() {
				final String announcement = Utility.randomElement(announcements);
				World.sendGlobalMessage(iconNumber + newsColor + " " + announcement);
			}

			@Override
			public void onStop() {
			}
		});
	}
	
}
