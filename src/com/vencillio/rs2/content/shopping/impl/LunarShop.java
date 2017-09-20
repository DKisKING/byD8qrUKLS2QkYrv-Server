package com.vencillio.rs2.content.shopping.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.content.interfaces.impl.QuestTab;
import com.vencillio.rs2.content.shopping.Shop;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Lunar store
 * 
 * @author Dez
 */
public class LunarShop extends Shop {

	/**
	 * Id of Lunar shop
	 */
	public static final int SHOP_ID = 359;

	/**
	 * Price of items in Lunar store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {
	
	case 4151:
		return 400000;
	case 6585:
		return 600000;
	case 11840:
		return 500000;
	case 10551:
		return 400000;
	case 4214:
		return 300000;
	case 391:
		return 7;
	case 4561:
		return 20;
	case 19484:
		return 10;
	
		
		

	}
	return 2147000000;
}

/**
 * All items in Lunar
 */
public LunarShop() {
	super(SHOP_ID, new Item[] { 
			new Item(4151),
			new Item(6585),
			new Item(11840),
			new Item(10551),
			new Item(4214),
			new Item(391,10000),
			new Item(4561,10000),
			new Item(19484,10000),
			
			}, 
			false, "tyras Point Shop");
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

		if (player.getlunarPoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Tyras Points to buy that."));
			return;
		}

		player.setlunarPoints(player.getlunarPoints() - amount * getPrice(id));

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
		return "Tyras Points";
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
