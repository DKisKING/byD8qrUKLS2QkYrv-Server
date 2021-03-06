package com.vencillio.rs2.content.shopping.impl;

import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.content.interfaces.impl.QuestTab;
import com.vencillio.rs2.content.shopping.Shop;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Pvm store
 * 
 * @author Dez
 */
public class PvmShop extends Shop {

	/**
	 * Id of Pvm shop
	 */
	public static final int SHOP_ID = 353;

	/**
	 * Price of items in Pvm store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {
	case 12695:
		return 15;
	case 989:
		return 10;
	case 12792:
		return 100;
	case 2379:
		return 50;
	case 3486:
		return 150;
	case 4692:
		return 25;
	case 3481:
		return 200;
	case 9433:
		return 25;
	case 3483:
		return 200;
	case 3485:
		return 200;
	case 12391:
		return 100;
	case 3488:
		return 150;
	case 12389:
		return 200;
	case 11840:
		return 50;
	case 6585:
		return 200;
	case 11335:
		return 250;
	case 2513:
		return 85;
	case 4087:
		return 35;
	case 7462:
		return 75;
	case 7461:
		return 45;
	case 7460:
		return 40;
	case 7459:
		return 35;
	case 7458:
		return 30;
	case 10553:
		return 45;
	case 10552:
		return 45;
	case 6528:
		return 20;
	case 6524:
		return 25;
	case 6525:
		return 20;
	case 6526:
		return 35;
	case 6527:
		return 45;
	case 4153:
		return 20;
	case 10551:
		return 150;
	case 12954:
		return 250;
	case 8850:
		return 150;
	case 2677:
		return 35;
		

	}
	return 2147483647;
}

/**
 * All items in Pvm store
 */
public PvmShop() {
	super(SHOP_ID, new Item[] { new Item(12695), new Item(989), new Item(2677), new Item(9433), new Item(12792), new Item(2379), new Item(3486), new Item(3481), new Item(3483), new Item(3485), new Item(12391), new Item(3488), new Item(12389), new Item(6585), new Item(11840), new Item(11335), new Item(2513), new Item(4087), new Item(7462), new Item(7461), new Item(7460), new Item(7459), new Item(7458), new Item(10553), new Item(10552), new Item(10551), new Item(6528), new Item(6524), new Item(6525), new Item(6526), new Item(6527), new Item(4153), new Item(12954), new Item(8850), new Item(4692) },
			false, "PVM Points Shop");
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

		if (player.getpvmPoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough PVM points to buy that."));
			return;
		}

		player.setpvmPoints(player.getpvmPoints() - amount * getPrice(id));

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
		return "PVM Points";
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
