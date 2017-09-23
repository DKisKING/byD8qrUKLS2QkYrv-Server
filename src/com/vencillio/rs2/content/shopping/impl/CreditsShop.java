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
public class CreditsShop extends Shop {

	/**
	 * Id of shop
	 */
	public static final int SHOP_ID = 94;

	/**
	 * Prices of item in shop
	 * 
	 * @param id
	 * @return
	 */
	//prices for shop/// case +item id.... return is the item value 
	public static final int getPrice(int id) {
	switch (id) {
	case 9925:
		return 150; //skeleton mask
	case 9924:
		return 150; //skeleton shirt
	case 9923:
		return 150; //skeleton legs
	case 9921:
		return 150; //skeleton boots
	case 9922:
		return 150; //skeleton gloves
	case 12845:
		return 375; //grim reaper hood
	case 1419:
		return 375; //scythe
	case 9920:
		return 112; //jack 'o lantern mask
	case 11847:
		return 225; //black h'ween mask
	case 11021:
		return 150; //chicken head
	case 11020:
		return 150; //chicken wings (body)
	case 11022: 
		return 150; //chicken legs
	case 11019:
		return 150; //chicken boots
	case 1037:
		return 150; //bunny ears
	case 13182:
		return 150; //bunny feet
	case 4565:
		return 150; //easter basket
	case 4566:
		return 150; //rubber chicken
	case 1053:
		return 150; //green 'ween
	case 1055:
		return 150; //blue 'ween
	case 1057:
		return 150; //red 'ween
	case 13175:
		return 300; //h'ween pack
	case 1050:
		return 200; //santa hat
	case 13343:
		return 500; //black santa hat
	case 13344:
		return 500; //inverted santa hat
	case 5607:
		return 150; //grain
	case 5609:
		return 150; //chicken
	case 1038:
		return 300; //red partyhat
	case 1040:
		return 300; //yellow partyhat
	case 1042:
		return 300; //blue partyhat
	case 1044:
		return 300; //green partyhat
	case 1046:
		return 300; //purple partyhat
	case 1048:
		return 300; //white partyhat
	case 13173:
		return 1500; //partyhat set
	case 11862:
		return 500; //black partyhat
	case 11863:
		return 500; //rainbow partyhat
	case 12399:
		return 500; //phat and specs
	}

	return 1500;
}

/**
 * Items in shop
 */
	//items that show up in shop
public CreditsShop() {
	super(SHOP_ID, new Item[] { 
			new Item(9925),
			new Item(9924),
			new Item(9923),
			new Item(9921),
			new Item(9922),
			new Item(12845),
			new Item(1419),
			new Item(9920),
			new Item(11847),
			new Item(11021),
			new Item(11020),
			new Item(11022),
			new Item(11019),
			new Item(1037),
			new Item(13182),
			new Item(4565),
			new Item(4566),
			new Item(1053),
			new Item(1055),
			new Item(1057),
			new Item(13175),
			new Item(1050),
			new Item(13343),
			new Item(13344),
			new Item(5607),
			new Item(5609),
			new Item(1038),
			new Item(1040),
			new Item(1042),
			new Item(1044),
			new Item(1046),
			new Item(1048),
			new Item(13173),
			new Item(11862),
			new Item(11863),
			new Item(12399),
			
	}, false, "Contributor's Cosmetic Shop");
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
