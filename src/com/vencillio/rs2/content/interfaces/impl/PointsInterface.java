package com.vencillio.rs2.content.interfaces.impl;

import com.vencillio.core.util.Utility;
import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.entity.player.Player;

public class PointsInterface extends InterfaceHandler {
	
	public PointsInterface(Player player) {
		super(player);
	}

	private final String[] text = {
			"@dre@Credits: @blu@" + Utility.format(player.getCredits()),
			"@dre@Achievement points: @blu@" + Utility.format(player.getAchievementsPoints()),
			"@dre@Vote points: @blu@" + Utility.format(player.getVotePoints()),
			"@dre@Bounty points: @blu@" + Utility.format(player.getBountyPoints()),
			"@dre@Boss points: @blu@" + Utility.format(player.getbossPoints()),
			"@dre@PVM points: @blu@" + Utility.format(player.getpvmPoints()),
			"@dre@Slayer points: @blu@" + Utility.format(player.getSlayerPoints()),
			"@dre@Prestige points: @blu@" + Utility.format(player.getPrestigePoints()),
			"@dre@Pest Control points: @blu@" + Utility.format(player.getPestPoints()),
			"@dre@Mage Arena points: @blu@" + Utility.format(player.getArenaPoints()),
			"@dre@Weapon Game points: @blu@" + Utility.format(player.getWeaponPoints()),
			"@dre@Trivia Points: @blu@" +Utility.format(player.gettriviaPoints()),
			"@dre@-----Skill Points-----",
			"@dre@Prayer Points: @gre@" +Utility.format(player.getprayerPoints()),
			"@dre@Runecrafting Points: @gre@" +Utility.format(player.getrunecraftingPoints()),
			"@dre@Hunting Points: @gre@" +Utility.format(player.gethunterPoints()),
			"@dre@Herblore Points: @gre@" +Utility.format(player.getherblorePoints()),
			"@dre@Thieving Points: @gre@" +Utility.format(player.getthievePoints()),
			"@dre@Crafting Points: @gre@" +Utility.format(player.getcraftingPoints()),
			"@dre@Fletching Points: @gre@" +Utility.format(player.getfletchingPoints()),
			"@dre@Mining Points: @gre@" +Utility.format(player.getminingPoints()),
			"@dre@Smithing Points: @gre@" +Utility.format(player.getSmithingPoints()),
			"@dre@Fishing Points: @gre@" +Utility.format(player.getfishingPoints()),
			"@dre@Cooking Points: @gre@" +Utility.format(player.getcookingPoints()),
			"@dre@Firemaking Points: @gre@" +Utility.format(player.getfiremakingPoints()),
			"@dre@Woodcutting Points: @gre@" +Utility.format(player.getwoodcuttingPoints()),
			"@dre@Farming Points: @gre@" +Utility.format(player.getfarmingPoints()),
			"",
			"",
	};

	@Override
	protected String[] text() {
		return text;
	}

	@Override
	protected int startingLine() {
		return 8145;
	}

}

