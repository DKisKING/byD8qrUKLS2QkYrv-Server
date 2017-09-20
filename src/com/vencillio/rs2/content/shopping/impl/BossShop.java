package com.vencillio.rs2.content.shopping.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.content.interfaces.impl.QuestTab;
import com.vencillio.rs2.content.shopping.Shop;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Boss store
 * 
 * @author Dez
 */
public class BossShop extends Shop {

	/**
	 * Id of Boss shop
	 */
	public static final int SHOP_ID = 349;

	/**
	 * Price of items in Boss Skill store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {
	
	case 373:
		return 1;
	case 385:
		return 5;
	case 391:
		return 10;
	case 7060:
		return 15;
	case 12695:
		return 100;
	case 157:
		return 35;
	case 145:
		return 35;
	case 163:
		return 35;
	case 181:
		return 35;
	case 3016:
		return 35;

	}
	return 2147483647;
}

/**
 * All items in hunter
 */
public BossShop() {
	super(SHOP_ID, new Item[] {
			new Item(373,1000),
			new Item(385,1000),
			new Item(391,1000),
			new Item(7060,1000),
			new Item(12695,1000),
			new Item(157,1000),
			new Item(145,1000),
			new Item(163,1000),
			new Item(181,1000),
			new Item(3016,1000)
			}, 
			false, "Boss Point Supplies Shop");
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

		if (player.getbossPoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Boss points to buy that."));
			return;
		}

		player.setbossPoints(player.getbossPoints() - amount * getPrice(id));

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
		return "Boss Points";
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
