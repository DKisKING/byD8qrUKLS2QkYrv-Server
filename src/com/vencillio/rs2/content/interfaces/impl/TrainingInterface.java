package com.vencillio.rs2.content.interfaces.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.entity.player.Player;

/**
 * Handles the training teleport interface
 * @author Daniel
 *
 */
public class TrainingInterface extends InterfaceHandler {
	
	public TrainingInterface(Player player) {
		super(player);
	}

	private final String[] text = {
			"Ghosts Training Zone",
			"Rock Crabs",
			"Nieve's Slayer Dungeon(NEW!)",
			"Hill Giants",
			"Yaks",
			"Brimhaven Dungeon",
			"Taverly Dungeon",
			"Slayer Tower",
			"Lava Dragons",
			"Mithril Dragons",
	};

	@Override
	protected String[] text() {
		return text;
	}

	@Override
	protected int startingLine() {
		return 61051;
	}

}

