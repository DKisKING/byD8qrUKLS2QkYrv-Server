package com.vencillio.rs2.content;

import com.vencillio.core.util.Utility;
import com.vencillio.rs2.entity.World;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

public class Yelling {

	public static final String YELL_COOLDOWN_KEY = "yellcooldown";

	public static String send;

	public static void yell(Player player, String message) {
		
		message = Utility.capitalizeFirstLetter(message);

		int rights = player.getRights();
		
		if (rights == 1) {
			send = "[<shad=0>@blu@Moderator</col></shad>] <img=0>@bla@" + player.getUsername() + "</col>:<shad=0>@blu@ " + message;
		} else if (rights == 2) {
			send = "[<shad=0><col=FFFF00>Administrator</col></shad>]  <img=1>" + player.getUsername() + "</col>:<shad=0><col=FFFF00> " + message;
		} else if (rights == 3) {
			send = "[<shad=0>@mag@Owner</col></shad>] <img=2><shad=0>@bla@" + player.getUsername() + "</shad></col>:<shad=0>@mag@ " + message;
		} else if (rights == 4) {
			send = "[<shad=0>@dre@Developer</col></shad>] <img=3>@dre@" + player.getUsername() + "</col>:<shad=0>@dre@ " + message;
		} else if (rights == 5) {
			send = "[<col=D11717>Donator</col>] <img=4>" + player.getUsername() + "</col>:<col=D11717> " + message;
		} else if (rights == 6) {
			send = "[<col=0956AD>Super</col>] <img=5>" + player.getUsername() + "</col>:<col=0956AD> " + message;
		} else if (rights == 7) {
			send = "[<col=4D8528>Legendary</col>] <img=6>" + player.getUsername() + "</col>:<col=4D8528> " + message;
		} else if (rights == 8) {
			send = "[<col=971FF2>Elite</col>] <img=7>" + player.getUsername() + "</col>:<col=971FF2> " + message;
		} else if (rights == 9) {
			send = "[<shad=0>@blu@Server Support</col></shad>] <img=8>" + player.getUsername() + "</col>:@blu@ " + message;
		
		} else {

			if (player.getRights() == 0) {
				 player.send (new SendMessage("Only members can Yell."));
				if (player.getAttributes().get("yellcooldown") == null) {
					player.getAttributes().set("yellcooldown", Long.valueOf(System.currentTimeMillis()));
				} else if (System.currentTimeMillis() - ((Long) player.getAttributes().get("yellcooldown")).longValue() < 3000L) {
					player.getClient().queueOutgoingPacket(new SendMessage("You must wait a few seconds before yelling again."));
					return;
				}
				
				player.getAttributes().set("yellcooldown", Long.valueOf(System.currentTimeMillis()));
			}
			return;
		}

		if (player.isMuted()) {
			player.getClient().queueOutgoingPacket(new SendMessage("You are muted and cannot yell."));
			return;
		}

		if (player.isYellMuted()) {
			player.getClient().queueOutgoingPacket(new SendMessage("You are muted are not allowed to yell."));
			return;
		}

		if (message.contains("<")) {
			player.getClient().queueOutgoingPacket(new SendMessage("You cannot use text arguments when yelling."));
			return;
		}


		for (Player i : World.getPlayers())
			if (i != null && send != null)
				i.getClient().queueOutgoingPacket(new SendMessage(send));
	}
}
