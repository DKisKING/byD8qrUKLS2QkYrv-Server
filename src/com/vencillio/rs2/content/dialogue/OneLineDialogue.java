package com.vencillio.rs2.content.dialogue;

import java.util.HashMap;

import com.vencillio.rs2.entity.player.Player;

public class OneLineDialogue {

	private static final HashMap<Integer, String> idsForChat = new HashMap<Integer, String>();

	public static void declare() {
		idsForChat.put(462, "Welcome to the Mages' guild!");
		idsForChat.put(781, "Welcome to the Skiller Zone! You can train your skills here.");
		idsForChat.put(553, "Hello there, I've got all kinds of magical supply!");
		idsForChat.put(5919, "You can exchange your Marks of Grace for some Agility armour.");
	}

	public static boolean doOneLineChat(Player player, int id) {
		if (idsForChat.containsKey(id)) {
			DialogueManager.sendNpcChat(player, id, Emotion.HAPPY, idsForChat.get(id));
			return true;
		}
		return false;
	}

}
