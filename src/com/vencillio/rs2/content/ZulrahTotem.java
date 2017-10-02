package com.vencillio.rs2.content;

import java.util.Arrays;

import com.vencillio.core.util.Utility;
import com.vencillio.core.util.chance.Chance;
import com.vencillio.core.util.chance.WeightedChance;
import com.vencillio.rs2.entity.World;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles Mystery Box rewards
 * @author DK
 *
 */
public class ZulrahTotem {
	/**
	 * Mystery box Identification
	 */
	private final static Item TOTEM = new Item(749);
	
	/**
	 * All possible loots from TOTEM
	 */
	public static Chance<Item> LOOTS = new Chance<Item>(Arrays.asList(
			new WeightedChance<Item>(WeightedChance.COMMON, new Item(12934, 1000)), //Zulrah's Scales
			new WeightedChance<Item>(WeightedChance.UNCOMMON, new Item(6571, 1)), //Uncut Onyx
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12922, 1)), //Tanzinite Fang
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12927, 1)), //Serpentine Visage
			new WeightedChance<Item>(WeightedChance.RARE, new Item(12932, 1)), //Magic Fang
			new WeightedChance<Item>(WeightedChance.VERY_RARE, new Item(12921, 1)) //Pet
			));
	
	/**
	 * Handles opening the TOTEM
	 * @param player
	 */
	public static void open(Player player) {
		Item reward = LOOTS.nextObject().get(); 
		String name = reward.getDefinition().getName();
		String formatted_name = Utility.getAOrAn(name) + " " + name;
		player.getInventory().remove(TOTEM);
		player.getInventory().addOrCreateGroundItem(reward);
		player.send(new SendMessage("You have opened the box and were rewarded with " + formatted_name + " ."));
		if (reward.getDefinition().getGeneralPrice() >= 1) {
			World.sendGlobalMessage("<shad=0>@gre@" + player.determineIcon(player) + " " + player.getUsername() + " has recieved " + formatted_name + " from a Zul-Andra Totem!");
		}
	}

}
