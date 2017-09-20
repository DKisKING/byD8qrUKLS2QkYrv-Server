package com.vencillio.rs2.content.shopping.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.content.interfaces.impl.QuestTab;
import com.vencillio.rs2.content.shopping.Shop;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Bounty store
 * 
 * @author Daniel
 */
public class BountyShop extends Shop {

	/**
	 * Id of Bounty shop
	 */
	public static final int SHOP_ID = 7;

	/**
	 * Price of items in Bounty store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {
	case 5000: // Vesta
	case 5001: // Vesta
	case 5002: // Vesta
	case 5003: // Vesta
		return 20_000_000; // Vesta Price
	case 5008: // Statius
	case 5009: // Statius
	case 5010: // Statius
	case 5011: // Statius
		return 20_000_000; // Statius Price
	case 13188: // Dragon Claws
		return 35_000_000; //Dragon Claws Price
	case 19553: // Torture Necklace
		return 30_000_000;
	case 6585: //Fury
		return 850_000; //Fury
	case 19550: // Ring of Suffering
		return 32_500_000;
	case 4151: //Whip
		return 900_000; //Whip Price
	case 19481: // Heavy Ballista
		return 25_000_000;
	case 19484: // Dragon Javelin
		return 1000;
	case 2577: // Ranger Boots
		return 120000;
	case 2581: // Robin Hood
		return 200000;
	}
	return 2147483647;
}

/**
 * All items in Bounty store
 */
public BountyShop() {
	super(SHOP_ID, new Item[] { 
	new Item(5000), 
	new Item(5001), 
	new Item(5002), 
	new Item(5003),
	new Item(5008), 
	new Item(5009), 
	new Item(5010), 
	new Item(5011), 
	new Item(13188),
	new Item(19553),
	new Item(6585), // FURY
	new Item(19550),
	new Item(4151), // Whip
	new Item(19481), //Heavy Ballista
	new Item(19484,500), // Javelins
	new Item(2577),
	new Item(2581),
	}, false, "Bounty Store");
}

	@Override
	public void buy(Player player, int slot, int id, int amount) {
		if (!hasItem(slot, id))
			return;
		if (get(slot).getAmount() == 0)
			return;
		if (amount > get(slot).getAmount()) {
			amount = get(slot).getAmount();
		}

		Item buying = new Item(id, amount);

		if (!player.getInventory().hasSpaceFor(buying)) {
			if (!buying.getDefinition().isStackable()) {
				int slots = player.getInventory().getFreeSlots();
				if (slots > 0) {
					buying.setAmount(slots);
					amount = slots;
				} else {
					player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough inventory space to buy this item."));
				}
			} else {
				player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough inventory space to buy this item."));
				return;
			}
		}

		if (player.getBountyPoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Bounty points to buy that."));
			return;
		}

		player.setBountyPoints(player.getBountyPoints() - amount * getPrice(id));

		InterfaceHandler.writeText(new QuestTab(player));

		player.getInventory().add(buying);
		update();
	}

	@Override
	public int getBuyPrice(int id) {
		return 0;
	}

	@Override
	public String getCurrencyName() {
		return "Bounty points";
	}

	@Override
	public int getSellPrice(int id) {
		return getPrice(id);
	}

	@Override
	public boolean sell(Player player, int id, int amount) {
		player.getClient().queueOutgoingPacket(new SendMessage("You cannot sell items to this shop."));
		return false;
	}
}
