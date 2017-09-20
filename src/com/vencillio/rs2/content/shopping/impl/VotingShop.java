package com.vencillio.rs2.content.shopping.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.content.interfaces.impl.QuestTab;
import com.vencillio.rs2.content.shopping.Shop;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Voting store
 * 
 * @author Daniel
 */
public class VotingShop extends Shop {

	/**
	 * Id of Bounty shop
	 */
	public static final int SHOP_ID = 92;

	/**
	 * Price of items in Bounty store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {
	
	case 6199: // Mystery Box
	case 7462: // Barrows Gloves
	case 2643: // Black Cav
	case 9470: // Gnome Scarf
	case 1837: // Desert Boots
	case 5607: // Sack of Grain
		return 4;
	case 6585: // Fury
		return 25;
	case 4151: // Whip
		return 30;
	case 13190: // Member Bond
		return 200;
	case 13188: // Dragon Claws
		return 100;
	

	}
	return 2147483647;
}

/**
 * All items in Bounty store
 */
public VotingShop() {
	super(SHOP_ID, new Item[] {
			new Item(6199), // Mystery Box
			new Item(7462), // Barrows Gloves
			new Item(2643), // Black Cav
			new Item(9470), // Gnome Scarf
			new Item(1837), // Desert Boots
			new Item(6585), // Fury
			new Item(4151), // Whip
			new Item(5607), // Sack of Grain
			new Item(13190), // Member Bond
			new Item(13188), // Dragon Claws
	}, false, "Vote Point Store ~ tyras");
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

		if (player.getVotePoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Vote points to buy that."));
			return;
		}

		player.setVotePoints(player.getVotePoints() - amount * getPrice(id));

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
		return "Vote points";
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
