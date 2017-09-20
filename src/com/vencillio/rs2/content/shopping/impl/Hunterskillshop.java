package com.vencillio.rs2.content.shopping.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.content.interfaces.impl.QuestTab;
import com.vencillio.rs2.content.shopping.Shop;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Hunter store
 * 
 * @author Dez
 */
public class Hunterskillshop extends Shop {

	/**
	 * Id of Hunter shop
	 */
	public static final int SHOP_ID = 351;

	/**
	 * Price of items in Hunter Skill store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {
	
	case 10045:
		return 50;
	case 10043:
		return 50;
	case 10041:
		return 50;
	case 10039:
		return 100;
	case 10037:
		return 100;
	case 10035:
		return 100;
	case 10075:
		return 100;
	case 10069:
		return 150;
	case 10071:
		return 250;
	case 11259:
		return 150;
	case 2528:
		return 25;
	case 2990:
		return 50;
	case 2991:
		return 100;
	case 2992:
		return 150;
	case 2993:
		return 200;
	case 2994:
		return 250;
	case 2995:
		return 300;
	case 10051:
		return 75;
	case 10049:
		return 75;
	case 10047:
		return 75;
	case 10149:
		return 100;
	case 10146:
		return 150;
	case 10147:
		return 200;
	case 10148:
		return 300;
	case 9976:
		return 125;
	case 9977:
		return 175;
	case 11959:
		return 350;
	case 9957:
		return 75;
	case 10023:
		return 250;

	}
	return 2147483647;
}

/**
 * All items in hunter
 */
public Hunterskillshop() {
	super(SHOP_ID, new Item[] { 
			new Item(10045),
			new Item(10043),
			new Item(10041),
			new Item(10051),
			new Item(10049),
			new Item(10047),
			new Item(10039),
			new Item(10037),
			new Item(10035),
			new Item(10075),
			new Item(11259),
			new Item(10069),
			new Item(10071),
			new Item(2990), 
			new Item(2991), 
			new Item(2992), 
			new Item(2993), 
			new Item(2994), 
			new Item(2995), 
			new Item(10149),
			new Item(10146),
			new Item(10147),
			new Item(10148),
			new Item(9976), 
			new Item(9977), 
			new Item(11959),
			new Item(9957),
			new Item(10023)
			}, 
			false, "Hunting Skillpoint Shop");
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

		if (player.gethunterPoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Hunter points to buy that."));
			return;
		}

		player.sethunterPoints(player.gethunterPoints() - amount * getPrice(id));

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
		return "Hunter Points";
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
