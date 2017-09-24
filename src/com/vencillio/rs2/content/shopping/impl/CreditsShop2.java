package com.vencillio.rs2.content.shopping.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.content.interfaces.impl.QuestTab;
import com.vencillio.rs2.content.shopping.Shop;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Shop for pest credits
 * 
 * @author Daniel
 */
public class CreditsShop2 extends Shop {

	/**
	 * Id of shop
	 */
	public static final int SHOP_ID = 90;

	/**
	 * Prices of item in shop
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {	

	case 13442:		//anglerfish
		return 150;
	case 3145:		//karambwan
		return 120;
	case 13067:		//super sets
		return 50;
	case 454:		//coal
		return 150;
	case 7409:		//magic secataeurs
		return 50;
	case 11941:		//looting bag
		return 50;
	case 13226:		//herb sack
		return 50;
	case 12791:		//rune pouch
		return 50;
	case 12934:		//zulrha's scales
		return 50;
	case 11230:		//dragon darts
		return 50;
	case 11212:		//dragon arrows	
		return 50;
	}
	return 150;
}

/**
 * Items in shop
 */
public CreditsShop2() {
	super(SHOP_ID, new Item[] { 
		new Item(13442,1000),	//anglerfish
		new Item(3145,1000),	//karambwan
		new Item(13067,250),	//super sets
		new Item(454,1000),		//coal
		new Item(7409),			//magic secateurs
		new Item(11941),		//looting bag
		new Item(13226),		//herb sack
		new Item(12791),		//rune pouch
		new Item(12394),		//zulrah's scales
		new Item(11230),		//dragon darts
		new Item(11212),		//dragon arrows

	}, false, "Contributor's Skilling and Resources Shop");
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

		if (player.getCredits() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Credits to buy that."));
			return;
		}

		player.setCredits(player.getCredits() - (amount * getPrice(id)));

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
		return "Credits";
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
