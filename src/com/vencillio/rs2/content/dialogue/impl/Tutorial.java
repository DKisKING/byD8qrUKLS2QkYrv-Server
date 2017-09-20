package com.vencillio.rs2.content.dialogue.impl;

import com.vencillio.rs2.content.StarterKit;
import com.vencillio.rs2.content.dialogue.Dialogue;
import com.vencillio.rs2.content.dialogue.DialogueManager;
import com.vencillio.rs2.content.dialogue.Emotion;
import com.vencillio.rs2.entity.Location;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.controllers.DefaultController;
import com.vencillio.rs2.entity.player.net.out.impl.SendInterface;
import com.vencillio.rs2.entity.player.net.out.impl.SendSidebarInterface;

public class Tutorial extends Dialogue {

	public static class TutorialController extends DefaultController {

		@Override
		public boolean canAttackNPC() {
			return false;
		}

		@Override
		public boolean canClick() {
			return false;
		}

		@Override
		public boolean canMove(Player p) {
			return false;
		}

		@Override
		public boolean canTeleport() {
			return false;
		}

		@Override
		public boolean canTrade() {
			return false;
		}

		@Override
		public void onDisconnect(Player p) {
		}

		@Override
		public boolean transitionOnWalk(Player p) {
			return false;
		}
	}

	public static final TutorialController TUTORIAL_CONTROLLER = new TutorialController();

	public static final int GUIDE = 306;

	public Tutorial(Player player) {
		this.player = player;
		player.setController(TUTORIAL_CONTROLLER);
	}

	@Override
	public boolean clickButton(int id) {
		switch (id) {
		case 9157:
			if (option == 1) {
				next = 3;
				execute();
			}
			return true;
		case 9158:
			if (option == 1) {
				next = 2;
				execute();
			}
			return true;
		}
		return false;
	}

	public static final int[] SIDEBAR_INTERFACE_IDS = { -1, -1, -1, 3213, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

	@Override
	public void execute() {

		for (int i = 0; i < SIDEBAR_INTERFACE_IDS.length; i++) {
			player.send(new SendSidebarInterface(i, SIDEBAR_INTERFACE_IDS[i]));
		}
		
		switch (next) {

		case 0:
			DialogueManager.sendNpcChat(player, GUIDE, Emotion.HAPPY_TALK, "Hello @blu@" + player.getUsername() + "</col>, Welcome to Tyras!", "Would you like a @red@QUICK 60 second</col> tutorial of our Home area?", "It is recomended that you do so for a better experience.", "::Vote to for points to purchase a MEMBERS credit bond!");
			next++;
			break;
		case 1:
			DialogueManager.sendOption(player, new String[] { "Yes. (HIGHLY RECOMMENDED)", "No." });
			option = 1;
			break;
		case 2:
			end();
			StarterKit.handle(player, 202051);
			player.send(new SendInterface(51750));
			break;
		case 3:
			nChat(new String[] {"Alright @blu@" + player.getUsername() + " Let's get started with the tutorial!"});
			break;
		case 4:
			nChat(new String[] {"@red@*Note*</col> if you do not see the orbs", "around your minimap, go to the", "@red@Settings menu</col> below your inventory to turn them on!"});
			break;
		case 5:
			nChat(new String[] { "Clicking on the @red@World Map</col> will allow you to teleport", "to various different locations.", "Including minigames, PvP, PvM, etc..." });
			break;
		case 6:
			tele(3092, 3490);
			nChat(new String[] { "Here you can Vote for Tyras and purchase rewards!", "@red@SPECIAL OFFER</col>", "You can now spend your Vote points on Credit Bonds", "to spend on @blu@Members</col> only items, Boss tasks & Titles!" });
			break;
		case 7:
			tele(3097, 3504);
			nChat(new String[] { "You can change your spell books and", "restore your prayer here." });
			break;
		case 8:
			tele(3095, 3470);
			nChat(new String[] { "Here you have Vannaka & Nieve.", "Vannaka can give you a @red@Slayer task</col>.", "Nieve can give you @red@Boss tasks</col> for 1 credit per task.", "Players can @red@VOTE</col> for credits to buy boss tasks" });
			break;
		case 9:
			tele(3100, 3501);
			nChat(new String[] { "Here is our Boss portal for custom made bosses.", "You can view a list of some of the new bosses", "added into Tyras." });
			break;
		case 10:
			tele(3092, 3494);
			nChat(new String[] { "This is our @red@Player-owned shops post</col>.", "They are located all over Tyras.", "You can sell/buy items from other players here." });
			break;
		case 11:
			tele(3080, 3511);
			nChat(new String[] { "These are the shops.", "You can buy all your necessary items from here.", "Click the Shops pillar at home", "OR type ::shops to visit them."});
			break;
		case 12:
			tele(2617, 3846);
			nChat(new String[] { "This is the fishing skill point shop.", "You recieve @red@Skill points</col> for training your skills", "throughout Tyras. @red@For example</col>, you will recieve", "1 fishing point for every fish you catch." });
			break;
		case 13:
			tele(3093, 3486);
			nChat(new String[] { "These are the PVM and Boss point shops.", "You recieve @red@Boss points</col> for killing bosses.", "You recieve @red@PVM points</col> for killing monsters", "around Tyras." });
			break;
		case 14:
			tele(3085, 3494);
			nChat(new String[] { "This is the home thieving area.", "This is a very easy method to make @red@QUICK MONEY</col>!" });
			break;
		case 15:
			tele(1815, 3856);
			nChat(new String[] { "Another great way to make money is by", "killing ghosts at the @red@Training zone.", "They commonly drop Crystal keys!" });
			break;
		case 16:
			tele(3085, 3499);
			nChat(new String[] { "This is the Emblem Trader.", "You can trade in your @red@bounty points</col>", "aquired from @red@killing players</col> here."});
			break;
		case 17:
			tele(3091, 3501);
			nChat(new String[] { "Captain Rott here is in charge of our @red@Prestige system</col>.", "After reaching 99 in a skill you may prestige it.", "After pretiging a skill, you will recieve points", "to spend in the Prestige shop."});
			break;
		case 18:
			tele(3092, 3492);
			nChat(new String[] { "If you are ever curious about a monster, talk to Spartan.", "Here you can view the @red@Drops</col> and some", "other useful Monster information." });
			break;
		case 19:
			tele(3089, 3492);
			nChat(new String[] { "If you ever need to change your clothes.", "Then you can speak to the makeover mage!" });
			break;
		case 20:
			tele(3087, 3494);
			nChat(new String[] { "If you have any more questions please speak to a", "<img=0>@blu@ Moderator</col> or any other staff member.", "For a list of commands, type @red@::commands</col>." });
			break;
		case 21:
			nChat(new String[] { "Don't forget to register at", "@red@www.projectreality.co</col>", "Make sure to @red@::vote</col> every 12 hours", "to help the server grow and recieve @red@REWARDS!</col>" });
			break;
		case 22:
			nChat(new String[] { "There are lots of other things to check out!", "So what are you waiting for?!", "your adventure begins..........", "HERE!" });
			break;
		case 23:
			end();
			StarterKit.handle(player, 202051);
			player.send(new SendInterface(51750));
			break;
		}

	}

	public void nChat(String[] chat) {
		DialogueManager.sendNpcChat(player, GUIDE, Emotion.PLAIN_EVIL, chat);
		next += 1;
	}

	public void pChat(String[] chat) {
		DialogueManager.sendPlayerChat(player, Emotion.SAD, chat);
		next += 1;
	}

	public void tele(int x, int y) {
		player.teleport(new Location(x, y, 0));
	}

	public void tele(int x, int y, int z) {
		player.teleport(new Location(x, y, z));
	}
}
