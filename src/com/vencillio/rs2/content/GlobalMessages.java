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
	private static String newsColor = "<col=013B4F>";

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
			"Do you Enjoy playing Tyras? Why not consider donating to help keep us running!",
			"Visit any makeover mage to change your characters appearance!",
			//"Automatic Voting is now set up, Vote via a voting booth or ::vote",
			"Curious on what boss drops what items? Check out our IG Drop Tables!",
			"Did you know you can get special armor rewarded through PK Points ONLY?",
			"Be sure to visit our website daily for regular news and announcements!",
			"Join us on Discord by using ::discord!",
			"Do you have a suggestion that would improve the server? Post it on our forums.",
			"Did you witness a player or staff member breaking the rules? Post a complaint.",
			"Please report ANY bugs found on our website, abusing them could result in a ban.",
			"Are you interested in making Youtube Videos for rewards? Contact Daniel.",
			//"Be sure to vote for the server every 12 hours using ::vote!",
			//"After voting claim any rewards with ::claimvote!",
			//"Be sure to ::claimvote after voting on EACH page or you will lose rewards!",
			"Did you know you can buy high tier food and potions at the Boss Point Shop?",
			"Are you short on money? Fishing and Thieving are great ways to make money!",
			"Members have the ability to open their bank out of PvP costing Member Credits!",
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
