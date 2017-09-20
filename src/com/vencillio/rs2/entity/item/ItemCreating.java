package com.vencillio.rs2.entity.item;

import java.util.ArrayList;

import com.vencillio.core.util.GameDefinitionLoader;
import com.vencillio.core.util.Utility;
import com.vencillio.rs2.content.Prestige;
import com.vencillio.rs2.content.dialogue.DialogueManager;
import com.vencillio.rs2.content.skill.Skills;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles creating items
 * @author Daniel
 *
 */
public enum ItemCreating {
	
	//ITEM("", new int[] {  }, new int[] {  }, new int[][] { }),
	
	CAP_AND_GOGGLES("Cap and goggles", new int[] { 9946 }, new int[] { 9945, 9472 }, new int[][] { }),
	RING_OF_WEALTH_INFUSED("Ring of wealth (i)", new int[] { 12785 }, new int[] { 2572, 12783 }, new int[][] { }),
	TOXIC_STAFF_OF_THE_DEAD("Toxic staff of the dead", new int[] { 12904 }, new int[] { 11791, 12932 }, new int[][] { { Skills.CRAFTING, 59 } }),
	TRIDENT_OF_THE_SWAMP("Trident of the swamp", new int[] { 12899 }, new int[] { 12932, 11907 }, new int[][] { { Skills.CRAFTING, 59 } }),
	DRAGON_PICKAXE("Dragon pickaxe", new int[] { 12797 }, new int[] { 11920, 12800 }, new int[][] { }),
	ODIUM_WARD("Odium ward", new int[] { 11926 }, new int[] { 11928, 11929, 11930 }, new int[][] { }),
	MALEDICTION_WARD("Malediction ward", new int[] { 11924 }, new int[] { 11931, 11932, 11933 }, new int[][] { }),	
	ODIUM_WARD_OR("Odium ward (or)", new int[] { 12807 }, new int[] { 11926, 12802 }, new int[][] { }),	
	MALEDICTION_WARD_OR("Malediction ward (or)", new int[] { 12806 }, new int[] { 11924, 12802 }, new int[][] { }),	
	MAGIC_SHORTBOW_INFUSED("Magic shortbow (i)", new int[] { 12788 }, new int[] { 12786, 861 }, new int[][] { }),
	FURY_AMULET_KIT("Amulet of fury (or)", new int[] { 12436 }, new int[] { 6585, 12526 }, new int[][] { }),
	ABYSSAL_TENTACLE("Abyssal tentacle", new int[] { 12006 }, new int[] { 12004, 4151 }, new int[][] { }),
	ARMADYL_GODSWORD("Armadyl godsword", new int[] { 11802 }, new int[] { 11798, 11810 }, new int[][] { }),
	BANDOS_GODSWORD("Bandos godsword", new int[] { 11804 }, new int[] { 11798, 11812 }, new int[][] { }),
	BLOWPIPE("Toxic blowpipe", new int[] { 12924 }, new int[] { 12922, 1755 }, new int[][] { }),	
	DARK_INFINITY("Dark infinity set", new int[] { 12458, 12457, 12459 }, new int[] { 12528, 6916, 6918, 6924 }, new int[][] { }),	
	GODSWORD_BLADE("Godsword blade", new int[] { 11798 }, new int[] { 11818, 11820, 11822 }, new int[][] { }),
	LIGHT_INFINITY("Light infinity set", new int[] { 12420, 12419, 12421 }, new int[] { 12530, 6916, 6918, 6924 }, new int[][] { }),
	MYSTIC_STEAM_BATTLESTAFF("Mystic steam battlestaff", new int[] { 12796 }, new int[] { 11789, 12798 }, new int[][] { }),
	STEAM_BATTLESTAFF("Steam battlestaff", new int[] { 12795 }, new int[] { 11787, 12798 }, new int[][] { }),
	SARADOMIN_BLESSED_SWORD("Saradomin blessed sword", new int[] { 12809 }, new int[] { 12804, 11838 }, new int[][] { }),
	SARADOMIN_GODSWORD("Saradomin godsword", new int[] { 11806 }, new int[] { 11798, 11814 }, new int[][] { }),
	ZAMORAK_GODSWORD("Zamorak godsword", new int[] { 11808 }, new int[] { 11798, 11816 }, new int[][] { }),
	SERPENTINE_VISAGE("Serpentine visage", new int[] { 12929 }, new int[] { 12927, 1755 }, new int[][] { { Skills.CRAFTING, 52 } }),
	DRAGONFIRE_SHIELD("Dragonfire shield", new int[] { 11283 }, new int[] { 1540, 11286 }, new int[][] { { Skills.SMITHING, 90 } }),	
	DRAGON_SHIELD_KIT("Dragon sq shield (g)", new int[] { 12418 }, new int[] { 12532, 1187 }, new int[][] { }),	
	DRAGON_CHAINBODY_KIT("Dragon chainbody (g)", new int[] { 12414 }, new int[] { 12534, 2513 }, new int[][] { }),		
	DRAGON_CHAINBODY_KIT_2("Dragon chainbody (g)", new int[] { 12414 }, new int[] { 12534, 3140 }, new int[][] { }),	
	DRAGON_HELM_KIT("Dragon full helm (g)", new int[] { 12417 }, new int[] { 12538, 11335 }, new int[][] { }),
	DRAGON_PLATELEGS_KIT("Dragon platelegs (g)", new int[] { 12415 }, new int[] { 12536, 4087 }, new int[][] { }),
	GRANITE_MAUL("Granite maul (or)", new int[] { 12848 }, new int[] { 12849, 4153 }, new int[][] { }),
	DRAGON_SQUARE("Dragon square shield", new int[] { 1187 }, new int[] { 2366, 2368 }, new int[][] { }),
	DRAGON_PLATESKIRT_KIT("Dragon plateskirt (g)", new int[] { 12416 }, new int[] { 12536, 4585 }, new int[][] { }),
	BLESSED_SPIRIT_SHIELD("Blessed spirit shield", new int[] { 12831 }, new int[] { 12829, 12833 }, new int[][] { {Skills.PRAYER, 10 } }),
	ARCANE_SPIRIT_SHIELD("Arcane spirit shield", new int[] { 12825 }, new int[] { 12831, 12827 }, new int[][] { { Skills.PRAYER, 10 } }),
	SPECTRAL_SPIRIT_SHIELD("Spectral spirit shield", new int[] { 12821 }, new int[] { 12831, 12823 }, new int[][] {{Skills.PRAYER, 10} }),
	ELYSIAN_SPIRIT_SHIELD("Elysian spirit shield", new int[] {12817}, new int[] {12831, 12819}, new int[][] {{Skills.PRAYER, 10}}),
	MITH_HELM("Mithril fullhelm (g)", new int[] { 12283 }, new int[] { 4692, 1059 }, new int[][] { { Skills.SMITHING, 50 } }),
	MITH_PLATEBODY("Mithril platebody (g)", new int[] { 12277 }, new int[] { 4692, 1121 }, new int[][] { { Skills.SMITHING, 50 } }),
	MITH_PLATELEGS("Mithril platelegs (g)", new int[] { 12279 }, new int[] { 4692, 1071 }, new int[][] { { Skills.SMITHING, 50 } }),
	MITH_PLATESKIRT("Mithril plateskirt (g)", new int[] { 12285 }, new int[] { 4692, 1085 }, new int[][] { { Skills.SMITHING, 50 } }),
	MITH_KITESHIELD("Mithril kiteshield (g)", new int[] { 12281 }, new int[] { 4692, 1097 }, new int[][] { { Skills.SMITHING, 50 } }),
	BLACK_HELM("Black fullhelm (g)", new int[] { 2595 }, new int[] { 4692, 1065 }, new int[][] { { Skills.SMITHING, 30 } }),
	BLACK_PLATEBODY("Black platebody (g)", new int[] { 2591 }, new int[] { 4692, 1125 }, new int[][] { { Skills.SMITHING, 30 } }),
	BLACK_PLATELEGS("Black platelegs (g)", new int[] { 2593 }, new int[] { 4692, 1077 }, new int[][] { { Skills.SMITHING, 30 } }),
	BLACK_PLATESKIRT("Black plateskirt (g)", new int[] { 3473 }, new int[] { 4692, 1089 }, new int[][] { { Skills.SMITHING, 30 } }),
	BLACK_KITESHIELD("Black kiteshield (g)", new int[] { 2597 }, new int[] { 4692, 1095 }, new int[][] { { Skills.SMITHING, 30 } }),
	IRON_HELM("Iron fullhelm (g)", new int[] { 12241 }, new int[] { 4692, 1053 }, new int[][] { { Skills.SMITHING, 10 } }),
	IRON_PLATEBODY("Iron platebody (g)", new int[] { 12235 }, new int[] { 4692, 1115 }, new int[][] { { Skills.SMITHING, 10 } }),
	IRON_PLATELEGS("Iron platelegs (g)", new int[] { 12237 }, new int[] { 4692, 1067 }, new int[][] { { Skills.SMITHING, 10 } }),
	IRON_PLATESKIRT("Iron plateskirt (g)", new int[] { 12239 }, new int[] { 4692, 1081 }, new int[][] { { Skills.SMITHING, 10 } }),
	IRON_KITESHIELD("Iron kiteshield (g)", new int[] { 12243 }, new int[] { 4692, 1091 }, new int[][] { { Skills.SMITHING, 10 } }),
	BRONZE_HELM("Bronze fullhelm (g)", new int[] { 12211 }, new int[] { 4692, 1055 }, new int[][] {}),
	BRONZE_PLATEBODY("Bronze platebody (g)", new int[] { 12205 }, new int[] { 4692, 1117 }, new int[][] {}),
	BRONZE_PLATELEGS("Bronze platelegs (g)", new int[] { 12207 }, new int[] { 4692, 1075 }, new int[][] {}),
	BRONZE_PLATESKIRT("Bronze plateskirt (g)", new int[] { 12209 }, new int[] { 4692, 1087 }, new int[][] {}),
	BRONZE_KITESHIELD("Bronze kiteshield (g)", new int[] { 12213 }, new int[] { 4692, 1089 }, new int[][] {}),
	ADDY_FULL_HELM("Adamant full helmet (g)", new int[] { 2613 }, new int[] { 4692, 1161 }, new int[][] { { Skills.SMITHING, 60 } }),
	ADDY_PLATEBODY("Adamant platebody (g)", new int[] { 2607 }, new int[] { 4692, 1123 }, new int[][] { { Skills.SMITHING, 60 } }),
	ADDY_PLATELEGS("Adamant platelegs (g)", new int[] { 2609 }, new int[] { 4692, 1073 }, new int[][] { { Skills.SMITHING, 60 } }),
	ADDY_PLATESKIRT("Adamant plateskirt (g)", new int[] { 3475 }, new int[] { 4692, 1091 }, new int[][] { { Skills.SMITHING, 60 } }),
	ADDY_KITE_SHIELD("Adamant kite shield (g)", new int[] { 2611 }, new int[] { 4692, 1199 }, new int[][] { { Skills.SMITHING, 60 } }),
	RUNE_FULL_HELM("Rune full helmet (g)", new int[] { 2619 }, new int[] { 4692, 1163 }, new int[][] { { Skills.SMITHING, 70 } }),
	RUNE_PLATEBODY("Rune platebody (g)", new int[] { 2615 }, new int[] { 4692, 1127 }, new int[][] { { Skills.SMITHING, 70 } }),
	RUNE_PLATELEGS("Rune platelegs (g)", new int[] { 2617 }, new int[] { 4692, 1079 }, new int[][] { { Skills.SMITHING, 70 } }),
	RUNE_PLATESKIRT("Rune plateskirt (g)", new int[] { 3476 }, new int[] { 4692, 1093 }, new int[][] { { Skills.SMITHING, 70 } }),
	RUNE_KITE_SHIELD("Rune kite shield (g)", new int[] { 2621 }, new int[] { 4692, 1201 }, new int[][] { { Skills.SMITHING, 70 } }),
	FIRE_MAX_CAPE("Fire max cape", new int[] { 13329 }, new int[] { 6570, 13280 }, new int[][] { }),
	AVAS_MAX_CAPE("Ava's max cape", new int[] { 13337 }, new int[] { 6570, 10499 }, new int[][] { }),
	ETERNAL_BOOTS("Eternal boots", new int[] { 13235 }, new int[] { 13227, 6920 }, new int[][] { { Skills.SMITHING, 75  } }),
	PEGASIAN_BOOTS("Pegasian boots", new int[] { 13237 }, new int[] { 13229, 10696 }, new int[][] { { Skills.SMITHING, 75  } }),
	PRIMORDIAL_BOOTS("Primordial boots", new int[] { 13239 }, new int[] { 13231, 11840 }, new int[][] { { Skills.SMITHING, 75  } }),
	LIGHT_BALLISTA("Light ballista", new int[] { 19478 }, new int[] { 19586, 19592, 19601 }, new int[][] { { Skills.FLETCHING, 85  } }),
	HEAVY_BALLISTA("Heavy ballista", new int[] { 19481 }, new int[] { 19589, 19592, 19601 }, new int[][] { { Skills.FLETCHING, 85  } }),
	AMULET_OF_TORTURE("Amulet of torture", new int[] { 19553 }, new int[] { 6566, 19529 }, new int[][] { { Skills.CRAFTING, 95  } }),
	NECKLACE_OF_ANGUISH("Necklace of anguish", new int[] { 19547 }, new int[] { 6565, 19529 }, new int[][] { { Skills.CRAFTING, 95  } }),
	RING_OF_SUFFERING("Ring of suffering", new int[] { 19550 }, new int[] { 6564, 19529 }, new int[][] { { Skills.CRAFTING, 95  } }),
	TORMENTED_BRACELET("Tormented bracelet", new int[] { 19544 }, new int[] { 6566, 11130 }, new int[][] { { Skills.CRAFTING, 95  } });
	
	private final String name;
	private final int[] product;
	private final int[] items;
	private final int[][] skills;
	private ItemCreating(String name, int[] product, int[] items, int[][] skills) {
		this.name = name;
		this.product = product;
		this.items = items;
		this.skills = skills;
	}
	
	public String getName() {
		return name;
	}
	
	public int[] getProduct() {
		return product;
	}
	
	public int[] getItems() {
		return items;
	}
	
	public int[][] getSkills() {
		return skills;
	}
	
	/**
	 * Gets the items 
	 * @param item1
	 * @param item2
	 * @return
	 */
	public static ItemCreating getDataForItems(int item1, int item2) {
		for(ItemCreating data : ItemCreating.values()) {
			int found = 0;
			for (int it : data.items) {
				if (it == item1 || it == item2) {
					found++;
				}
			}
			if (found >= 2) {
				return data;				
			}
		}
		return null;
	}
	
	/**
	 * Handles creating the items
	 * @param player
	 * @param item1
	 * @param item2
	 * @return
	 */
	public static boolean handle(Player player, int item1, int item2) {
		if (item1 == item2) {
			return false;			
		}
		
		ItemCreating data = ItemCreating.getDataForItems(item1, item2);
		
		if (data == null || !player.getInventory().hasItemId(item1) || !player.getInventory().hasItemId(item2)) {
			return false;			
		}
		
		for (int i = 0; i < data.getSkills().length; i++) {
			if (player.getSkill().getLevels()[data.getSkills()[i][0]] < data.getSkills()[i][1]) {
				DialogueManager.sendItem1(player, "You need a " + Prestige.getSkillName(data.getSkills()[i][0]) + " level of " + data.getSkills()[i][1] + " to do this!", data.getProduct()[0]);
				return true;
			}
		}
		
		ArrayList<String> required = new ArrayList<String>();
		
		for (int reqItem : data.items) {
			if (!player.getInventory().hasItemId(reqItem)) {
				player.send(new SendMessage("You do not have all the needed items to do this!"));
				required.add(GameDefinitionLoader.getItemDef(reqItem).getName());
				continue;
			}
		}
		
		if (!required.isEmpty()) {
			player.send(new SendMessage("@red@To make " + data.getName() + " you need: " + required + "."));
			required.clear();
			return false;
		}
		
		if (player.getInventory().getFreeSlots() < data.product.length) {
			player.send(new SendMessage("You need at least " + data.product.length + " free inventory space(s) to do this!"));
			return false;
		}
		
		for (int reqItem : data.items) {
			if (reqItem == 1755 || reqItem == 1595) {
				continue;				
			}
			player.getInventory().remove(reqItem);
		}
	
		for (int product : data.product) {
			player.getInventory().add(product, 1);
		}
		
		DialogueManager.sendItem1(player, "@dre@You have created " + Utility.getAOrAn(data.getName()) + " " + data.getName() + ".", data.getProduct()[0]);		
		return true;
	}

}
