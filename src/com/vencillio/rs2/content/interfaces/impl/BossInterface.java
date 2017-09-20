package com.vencillio.rs2.content.interfaces.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.entity.player.Player;

/**
 * Handles the boss teleport interface
 * @author Daniel
 *
 */
public class BossInterface extends InterfaceHandler {
	
	public BossInterface(Player player) {
		super(player);
	}

	private final String[] text = {
			"King Black Dragon [WILD]",
			"Sea Troll Queen",
			"Barrelchest",
			"Corporeal Beast",
			"Daggonoths Kings",
			"Godwars",
			"Zulrah",
			"Kraken",
			"Giant Mole",
			"Chaos Element [WILD]",
			"Callisto",
			"Scorpia [WILD]",
			"Vet'ion",
			"Venenatis(NEW!)",
			"Chaos Fanatic [WILD]",
			"Crazy archaeologist [WILD]",
			"Kalphite Queen",
			"Skeleton Trio(NEW!)"
			
	};

	@Override
	protected String[] text() {
		return text;
	}

	@Override
	protected int startingLine() {
		return 64051;
	}

}

