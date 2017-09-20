package com.vencillio.rs2.content.pets;

import com.vencillio.core.task.Task;
import com.vencillio.core.task.TaskQueue;
import com.vencillio.rs2.content.achievements.AchievementHandler;
import com.vencillio.rs2.content.achievements.AchievementList;
import com.vencillio.rs2.content.dialogue.DialogueManager;
import com.vencillio.rs2.entity.Animation;
import com.vencillio.rs2.entity.World;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.mob.Mob;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Handles boss pets
 * 
 * @author Daniel
 */
public class BossPets {

	/**
	 * Boss Pet data
	 * @author Daniel
	 *
	 */
	public enum PetData {
		
		//TODO:
		//PENANCE QUEEN
		//CHOMPY CHICK
		
		KALPHITE_PRINCESS_FLY(12654, 6637),
		HELLPUPPY(13247, 3099),
		SHADOW_HOUND(7916, 3449),
		INADEQUACY(4520, 3473),
		EVIL_CREATURE(10115, 1256),
		CUTE_CREATURE(10117, 1257),
		QUEEN_SPAWN(1, 5576), //TODO
		PENANCE_QUEEN(1, 5575), //TODO
		UNDEAD_OVERLORD(8131, 4496), //undead overlord boss pet
		SPIRITUAL_RANGER(6640, 3160),//plague
		SWAMP_LIZARD(10149, 2906), //hunter
		ORANGE_SALAMANDER(10146, 2903), //hunter
		RED_SALAMANDER(10147, 2904), //hunter
		BLACK_SALAMANDER(10148, 2905), //hunter
		CHINCHOMPA(9976, 2910), //hunter
		RED_CHINCHOMPA(9977, 2911), //hunter
		BLACK_CHINCHOMPA(11959, 2912), //hunter
		SPIKED_KEBBIT(9957, 1346), //hunter TODO OTHER KEBBITS!!!!
		FALCON(10023, 1342), //hunter
		INFERNAL_MAGE(4140, 446), //members boss pet
		RACCOON(6645, 1419), //thieving
		GOLDFISH(272, 4823), //fishing
		PYREFIEND(4138, 436), //firemaking
		MONKEY_CHILD(4033, 5268), //cooking
		BROOM(4179, 3845), //fletching
		CHOMPY(13071, 1475), //herblore
		TOOL_LEPRECHAUN(12359, 0), //farming
		FIREBIRD(1583, 4927), //smithing
		DWARF(7500, 296), //mining
		TREE_SPIRIT(771, 1163), //woodcutting
		SPIRIT(964, 5341), //prayer
		ABYSSAL_LEECH(7986, 2583), //runecrafting
		TOY_MOUSE(7769, 2781), //crafting
		CLOCKWORK_CAT(7771, 2782), //crafting
		TOY_SOLDIER(7761, 2779), //crafting
		TOY_DOLL(7765, 2780), //crafting
		TOY_PENGUIN(5565, 846), //STARTER
		HELL_CAT(7582, 1625), //DONOR SHOP 
		HELL_KITTEN(7583, 5597), //FREE SHOP 
		//VERAC_JR(15570, 4177), 
		RABBIT(9975, 1853), //FREE
		HELL_RAT(11047, 1062), //DONOR
		HELL_GOBLIN(10998, 1065), //DONOR
		MUTANT(10731, 1067), //DONOR
		HAM_ZANIK(8888, 4326), //DONOR
		ZANIK(8887, 4324), //DONOR
		SOULLESS_FOLLOWER(12840, 1072), //DONOR
		BLACK_DOG(8132, 4228), //FREE
		SHEPARD_DOG(7914, 111), //DONOR
		SHARK(7993, 1830), //FREE
		BLACK_SWAN(5076, 1832), //DONOR
		WHITE_SWAN(5077, 1831), //FREE
		//SHEEP_DOG(8132, 2817),
		ROCK_CRAB(1855, 102),
		BABY_RED_DRAGON(8134, 244),
		JAD(13225, 4010), 
		GHOST(553, 3627), //FREE 
		IMP(9952, 5728), //DONOR
		BUTTERFLY(9970, 1854), //DONOR
		//SPIRITUAL_WARRIOR(6640, 6219), 
		//SPIRITUAL_RANGER(6642, 6220), 
		//SPIRITUAL_MAGE(6644, 6221), 
		KALPHITE_PRINCESS_BUG(12647, 6638),
		SMOKE_DEVIL(12648, 6655),
		DARK_CORE(12816, 318),
		PRINCE_BLACK_DRAGON(12653, 4000),
		GREEN_SNAKELING(12921, 2130),
		RED_SNAKELING(12939, 2131),
		BLUE_SNAKELING(12940, 2132),
		CHAOS_ELEMENT(11995, 5907),
		KREE_ARRA(12649, 4003),
		CALLISTO(13178, 497),
		SCORPIAS_OFFSPRING(13181, 5547),
		VENENATIS(13177, 495),		
		VETION_PURPLE(13179, 5559),
		VETION_ORANGE(13180, 5560),
		BABY_MOLE(12646, 6635),
		KRAKEN(12655, 6640),
		DAGANNOTH_SUPRIME(12643, 4006),
		DAGANNOTH_RIME(12644, 4007),
		DAGANNOTH_REX(12645, 4008),
		GENERAL_GRAARDOR(12650, 4001), 
		COMMANDER_ZILYANA(12651, 4009),
		KRIL_TSUTSAROTH(12652, 4004);

		private final int itemID;
		private final int npcID;

		private PetData(int itemID, int npcID) {
			this.itemID = itemID;
			this.npcID = npcID;
		}

		public int getItem() {
			return itemID;
		}

		public int getNPC() {
			return npcID;
		}

		public static PetData forItem(int id) {
			for (PetData data : PetData.values())
				if (data.itemID == id)
					return data;
			return null;
		}

		public static PetData forNPC(int id) {
			for (PetData data : PetData.values())
				if (data.npcID == id)
					return data;
			return null;
		}
	}

	/**
	 * Handles spawning the pet
	 * @param player
	 * @param itemID
	 */
	public static boolean spawnPet(Player player, int itemID, boolean loot) {
		PetData data = PetData.forItem(itemID);

		if (data == null) {
			return false;
		}

		if (player.getBossPet() != null) {
			player.send(new SendMessage("You already have a pet summoned!"));
			return true;
		}

		player.getInventory().remove(new Item(itemID, 1));

		final Mob mob = new Mob(player, data.npcID, false, false, true, player.getLocation());
		mob.getFollowing().setIgnoreDistance(true);
		mob.getFollowing().setFollow(player);

		player.setBossPet(mob);
		player.setBossID(data.npcID);
		player.getUpdateFlags().sendAnimation(new Animation(827));
		player.face(player.getBossPet());
		

		if (loot) {
			AchievementHandler.activateAchievement(player, AchievementList.OBTAIN_1_BOSS_PET, 1);
			AchievementHandler.activateAchievement(player, AchievementList.OBTAIN_10_BOSS_PET, 1);
		} else {
			player.send(new SendMessage("You summoned your " + mob.getDefinition().getName() + "."));
		}
		return true;
	}

	/**
	 * Handles picking up the pet
	 * @param player
	 * @param npcID
	 * @return
	 */
	public static boolean pickupPet(Player player, Mob mob) {
		if (mob == null || World.getNpcs()[mob.getIndex()] == null) {
			return false;
		}
		PetData data = PetData.forNPC(mob.getId());

		if (data == null) {
			return false;
		}
		
		if (player.getBossPet() == null || player.getBossPet().isDead()) {
			return false;
		}
		
		if (player.getBossPet() != mob || mob.getOwner() != player) {
			DialogueManager.sendStatement(player, "This is not your pet!");
			return true;
		}
		
		if (player.getInventory().hasSpaceFor(new Item(data.getItem()))) {
			player.getInventory().add(new Item(data.getItem()));
		} else if (player.getBank().hasSpaceFor((new Item(data.getItem())))) {
			player.getBank().add((new Item(data.getItem())));
			player.getClient().queueOutgoingPacket(new SendMessage("Your pet has been added to your bank."));
		} else {
			player.getClient().queueOutgoingPacket(new SendMessage("You must free some inventory space to pick up your pet."));
			return false;
		}
		
		player.getUpdateFlags().sendAnimation(new Animation(827));
		player.face(player.getBossPet());
		
		TaskQueue.queue(new Task(player, 1, true) {
			@Override
			public void execute() {
				player.getBossPet().remove();
				player.setBossPet(null);
				stop();
			}

			@Override
			public void onStop() {
				player.send(new SendMessage("You have picked up your pet."));
			}
		});		
		
		return true;
	}
	
	/**
	 * Handles pets on logout
	 * @param player
	 * @return
	 */
	public static void onLogout(Player player) {
		if (player.getBossPet() != null) {
			
			PetData data = PetData.forNPC(player.getBossPet().getId());
			
			if (player.getInventory().hasSpaceFor(new Item(data.getItem()))) {
				player.getInventory().add(new Item(data.getItem()));
			} else if (player.getBank().hasSpaceFor((new Item(data.getItem())))) {
				player.getBank().add((new Item(data.getItem())));
			}
		}
	}
	
	/**
	 * Handles what happens on death
	 * @param player
	 */
	public static void onDeath(Player player) {
	if (player.getBossPet() != null) {
		
		PetData data = PetData.forNPC(player.getBossPet().getId());
		
	if (player.getInventory().hasSpaceFor(new Item(data.getItem()))) {
		player.getInventory().add(new Item(data.getItem()));
	} else if (player.getBank().hasSpaceFor((new Item(data.getItem())))) {
		player.getBank().add((new Item(data.getItem())));
	}
		}
			}
				}