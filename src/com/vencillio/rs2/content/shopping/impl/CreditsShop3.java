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
	//case 11832:	//Bandos plate	//ITEMS NO LONGER IN STORE! (MAY USE THEM IN FUTURE)
	//case 11834: 	//Bandos bottoms
		//return 150;
	//case 11836: 	//Bandos boots
		//return 37;
	//case 11826: 	//Armadyl helm
	//case 11828: 	//Armadyl body
	//case 11830: 	//Armadyl legs
		//return 112;
	//case 11283: 	//Dragon Fire Shield
			//return 75;
	//case 13235: 	//enternal boots
	//case 13239: 	//primordial boots
	//case 13237: 	//pegasian boots
		//return 112;
	//case 12926: 	//blowpipe
			//return 275;
	//case 11785: 	//armadyl crossbow
			//return 150;
	//case 11802: 	//arma gs
	//case 11806: 	//sara gs
	//case 11808: 	//zammy gs
	//case 11804: 	//bandos gs
			//return 150;
	//case 11773: 	//Berserker Ring (i)
	//case 11772: 	//Warriors Ring (i)
	//case 11771: 	//Archers Ring (i)
	//case 11770: 	//Seers Ring (i)
		//return 18;
	//case 19529: 	//zenyte shard
	//case 19553: 	//torture
	//case 19547:	//anguish
		//return 112;	
	case 4151: 		//Whip 75	// ITEMS CURRENTLY IN STORE
		return 50; 
	case 13265:		//abyssal dagger
		return 150;
	case 7142: 		//rapier
		return 400;
	case 13188: 	//dclaws
		return 150;	
	case 6585: 		//Fury
		return 50;
	case 7462: 		//barrows gloves
		return 50;
	case 11840: 	//dragon boots 
		return 20;
	case 2581:		//robin hood hat
		return 50;
	case 12596:		//ranger's tunic
		return 50;
	case 2577:		//ranger boots
		return 100;	
	case 6918: 		//infinity hat
		return 50;
	case 6916:		//infinity top
		return 50;	
	case 6924:		//infinity bottoms
		return 50;
	case 6920:		//infinity boots
		return 50;	
	case 6922:		//infinity gloves
		return 50;
	case 13379:		//shayzien helm
		return 100;
	case 13381:		//shayzien body
		return 100;
	case 13380:		//shayzien greaves
		return 100;
	case 13378:		//shayzien boots
		return 100;	
	case 13377:		//shayzien gloves
		return 100;
	}

	return 100;
}

/**
 * Items in shop
 */
public CreditsShop3() {
	super(SHOP_ID, new Item[] {
		//new Item(11832), 	//Bandos
		//new Item(11834), 	//Bandos
		//new Item(11836),	//bandos boots
		//new Item(11826),	//arma helm
		//new Item(11828), 	//ama chest
		//new Item(11830),	//arma legs
		//new Item(13235),	//boots
		//new Item(13237),	//boots
		//new Item(13239),	//eternal boots
		//new Item(11802),	//godswords
		//new Item(11806),
		//new Item(11808),
		//new Item(11804),
		//new Item(11283),	//dfs
		//new Item(12926), 	//blowpipe
		//new Item(11785), 	//crossbow
		//new Item(11773),	//berserker ring i
		//new Item(11772),	//warriors ring i
		//new Item(11771),	//archers ring i
		//new Item(11770),	//seers ring i
		//new Item(19529),	//zennyte shard
		//new Item(19553),	//torture
		//new Item(19547),	//anguish
		new Item(7142), 	//rapier
		new Item(4151),		//whip
		new Item(13265),	//abyssal dagger
		new Item(13188),	//dclaws
		new Item(6585),		//fury
		new Item(7462), 	//barrows gloves
		new Item(11840),	//dragon boots
		new Item(2581),		//robin hood hat
		new Item(12596),	//ranger's tunic
		new Item(2577),		//ranger boots
		new Item(6918),		//infinity hat
		new Item(6916),		//infinity top
		new Item(6924),		//infinity bottoms
		new Item(6920),		//infinity boots
		new Item(6922),		//infinity gloves
		new Item(13379),	//shayzien helm
		new Item(13381),	//shayzien body
		new Item(13380),	//shayzien greaves
		new Item(13378),	//shayzien boots
		new Item(13377),	//shayzien gloves
		
	}, false, "Contributor's Gear Shop");
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
