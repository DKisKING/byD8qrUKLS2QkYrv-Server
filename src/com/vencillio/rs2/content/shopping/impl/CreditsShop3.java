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
public class CreditsShop3 extends Shop {

	/**
	 * Id of shop
	 */
	public static final int SHOP_ID = 87;

	/**
	 * Prices of item in shop
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {
	case 11832: //Bandos plate 150
	case 11834: //Bandos bottoms 150
		return 112;
	case 11836: //Bandos boots 50
		return 37;
	case 11826: //Armadyl helm 150
	case 11828: //Armadyl body 150
	case 11830: //Armadyl legs 150
		return 112;
	case 13235: //enternal boots 150
	case 13239: //primordial boots 150
	case 13237: // pegasian boots 150
		return 112;
	case 11840: //dragon boots 50
		return 37;
	case 4151: //Whip 75
		return 56; 
	case 6585: //Fury 100
		return 75;
	case 11283: //Dragon Fire Shield 100
		return 75;
	case 7462: //barrows gloves 50
		return 37;
	case 11773: //Berserker Ring (i) 25
	case 11772: //Warriors Ring (i) 25
	case 11771: //Archers Ring (i) 25
	case 11770: //Seers Ring (i) 25
		return 18;
	case 7142: //rapier 250
		return 206;
	case 12926: //blowpipe 275
		return 275;
	case 11785: //armadyl crossbow 200
		return 150;
	case 19529: //zenyte shard 150
	case 19553: //torture 150
	case 19547://anguish 150
		return 112;	
	case 11802: //arma gs 200
	case 11806: //sara gs 200
	case 11808: //zammy gs 200
	case 11804: //bandos gs 200
		return 150;
	case 13188: //dclaws 150
		return 112;
	}

	return 100;
}

/**
 * Items in shop
 */
public CreditsShop3() {
	super(SHOP_ID, new Item[] {
		new Item(11832), //Bandos
		new Item(11834), //Bandos
		new Item(11836),// bandos boots
		new Item(11826),//arma helm
		new Item(11828), //ama chest
		new Item(11830),//arma legs
		new Item(13235),	//boots
		new Item(13237),	//boots
		new Item(13239),	//internal boots
		new Item(11840),	//dragon boots
		new Item(4151),	//whip
		new Item(6585),	//fury
		new Item(11283),	//dfs
		new Item(7462), //barrows gloves
		new Item(11773),
		new Item(11772),
		new Item(11771),
		new Item(11770),
		new Item(7142), //rappior
		new Item(12926), //blowpipe
		new Item(11785), //crossbow
		new Item(19529),//shard
		new Item(19553),//torture
		new Item(19547),//angwish
		new Item(13188),
		new Item(11802),
		new Item(11806),
		new Item(11808),
		new Item(11804),
			
	}, false, "Donator's Gear Shop - 25% Off!");
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
