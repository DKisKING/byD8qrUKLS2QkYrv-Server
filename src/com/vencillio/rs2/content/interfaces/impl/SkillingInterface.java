package com.vencillio.rs2.content.interfaces.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.entity.player.Player;

/**
 * Handles the skilling teleport interface
 * @author Daniel
 *
 */
public class SkillingInterface extends InterfaceHandler {
	
	public SkillingInterface(Player player) {
		super(player);
	}

	private final String[] text = {
			"Skilling Zone [NEW!]",
			"Wilderness Skilling Zone",
		//	"Crafting",
			"Agility",
		//	"Mining",
		//	"Smithing",
		//	"Fishing",
		//	"Woodcutting",
			"Farming",
			"Hunter(Coming soon!)",
			"",
			"",
			"",
			
	};

	@Override
	protected String[] text() {
		return text;
	}

	@Override
	protected int startingLine() {
		return 62051;
	}

}

