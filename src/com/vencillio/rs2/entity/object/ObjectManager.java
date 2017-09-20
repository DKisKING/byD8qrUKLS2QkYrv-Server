package com.vencillio.rs2.entity.object;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import com.vencillio.core.cache.map.MapLoading;
import com.vencillio.core.cache.map.RSObject;
import com.vencillio.core.cache.map.Region;
import com.vencillio.rs2.content.skill.firemaking.FireColor;
import com.vencillio.rs2.entity.Location;
import com.vencillio.rs2.entity.World;
import com.vencillio.rs2.entity.player.Player;

@SuppressWarnings("all")
public class ObjectManager {

	public static final int BLANK_OBJECT_ID = 2376;

	private static final List<GameObject> active = new LinkedList<GameObject>();
	private static final Deque<GameObject> register = new LinkedList<GameObject>();

	private static final Queue<GameObject> send = new ConcurrentLinkedQueue<GameObject>();

	public static void add(GameObject o) {
		active.add(o);
	}

	public static void addClippedObject(GameObject o) {
		register.add(o);
	}

	public static void declare() {

		for (GameObject i : active) {
			send(getBlankObject(i.getLocation()));
		}

		active.clear();
		
		/** Home Area */
		
		spawnWithObject(18772, 2842, 10213, 0, 10, 1);//MysteryBox - NEW HOME
		spawnWithObject(6249, 2838, 10206, 0, 10, 0);//Player Owned Store - NEW HOME
		spawnWithObject(16604, 2853, 10195, 0, 10, 0);//dream tree
		spawnWithObject(16604, 2856, 10193, 0, 10, 0);//dream tree
		spawnWithObject(2191, 2841, 10207, 0, 10, 1);//crystal chest
		
		
		
		
		/** Edgeville */
		
		spawnWithObject(409, 2842, 10222, 0, 10, 2);//prayAltar done - MOVED TO NEW HOME (EDGE)
		spawnWithObject(6943, 2547, 3168, 0, 10, 1); //bank 
		spawnWithObject(6943, 2547, 3169, 0, 10, 1); //bank 3091, 3496
		spawnWithObject(6943, 2547, 3170, 0, 10, 1); //bank 
		spawnWithObject(6943, 2547, 3171, 0, 10, 1); //bank 
		
		//spawnWithObject(6552, 3501, 3478, 0, 10, 2);//Ancient Altar
		//spawnWithObject(410, 3506, 3464, 0, 10, 0);//lunarAltar	
		
		spawnWithObject(412, 3097, 3506, 0, 10, 2);//spellbookAltar done - MOVED TO NEW HOME (EDGE)
		spawnWithObject(4875, 3084, 3496, 0, 10, 5);//Food stall done  - MOVED TO NEW HOME (EDGE)
		spawnWithObject(4876, 3084, 3495, 0, 10, 5);//General stall done  - MOVED TO NEW HOME (EDGE)
		spawnWithObject(4874, 3084, 3494, 0, 10, 5);//Crafting stall done - MOVED TO NEW HOME (EDGE)
		spawnWithObject(4877, 3084, 3493, 0, 10, 5);//Magic stall done - MOVED TO NEW HOME (EDGE)
		spawnWithObject(4878, 3084, 3492, 0, 10, 5);//Scimitar stall done - MOVED TO NEW HOME (EDGE)
		spawnWithObject(2191, 3091, 3496, 0, 10, 1);//Crystal chest done - MOVED TO NEW HOME (EDGE)
		spawnWithObject(6249, 3091, 3494, 0, 10, 0);//Player owned shops - MOVED TO NEW HOME (EDGE)
		spawnWithObject(26820, 1808, 3785, 0, 10, 1);//vote booth done - MOVED TO NEW HOME (EDGE)
		spawnWithObject(18772, 3089, 3498, 0, 10, 1);//MysteryBox chest - MOVED TO NEW HOME (EDGE)
		spawnWithObject(26645, 3085, 3509, 0, 10, 3);//ffa portal done
		spawnWithObject(13641, 3100, 3502, 0, 10, 0);//boss portal done - MOVED TO NEW HOME (EDGE)
		spawnWithObject(576, 1802, 3790, 0, 10, 0); //highscores done
		spawnWithObject(27785, 1822, 3784, 0, 10, 1);//shops teleport
		spawnWithObject(21773, 1782, 3788, 0, 10, 2);//cerberus
		spawnWithObject(18769, 1801, 3786, 0, 10, 1);//max cape rack
		spawnWithObject(13633, 1798, 3787, 0, 10, 0);//edgeville portal
		setClipToZero(1810, 3790, 0);
		setClipToZero(3095, 3957,0);
		deleteWithObject(3084, 3509, 0);
		deleteWithObject(3084, 3510, 0);
		deleteWithObject(3084, 3512, 0);
		deleteWithObject(3083, 3513, 0);
		setClipToZero(3084, 3509, 0);
		setClipToZero(3084, 3510, 0);
		setClipToZero(3084, 3512, 0);
		setClipToZero(3083, 3513, 0);
		setClipToZero(3081, 3511, 0);
		setClipToZero(3081, 3510, 0);
		
		deleteWithObject(3088, 3509, 0); //Edgeville barrel (Next to general store)
		setClipToZero(3088, 3509, 0); //Edgeville barrel (Next to general store)
		
		deleteWithObject(3092, 3496, 0); //Edgeville Chair in Bank
		setClipToZero(3092, 3496, 0); //Edgeville Chair in Bank
		
		deleteWithObject(3081, 3510, 0); //General Store Table
		
		deleteWithObject(3080, 3510, 0); //Crate in Edgeville store
		setClipToZero(3080, 3510, 0); //Crate in Edgeville store
		
		deleteWithObject(3078, 3510, 0); //Table in Edgeville store
		setClipToZero(3078, 3510, 0); //Table in Edgeville store
		
		deleteWithObject(3077, 3512, 0); //Stepladder in edgeville store
		setClipToZero(3077, 3512, 0); //Stepladder in edgeville store
		
		
		/* Skilling area */ //~MOVED TO FALADOR~
		//spawnWithObject(6943, 3004, 3382, 0, 10, 1);//Banks MOVED
		//spawnWithObject(6943, 3004, 3384, 0, 10, 1);//Banks MOVED
		//spawnWithObject(6943, 3004, 3381, 0, 10, 1);//Banks MOVED
		//spawnWithObject(6943, 3004, 3385, 0, 10, 1);//Banks MOVED
		
		//spawnWithObject(6943, 2980, 3388, 0, 10, 1);//Banks MOVED
		//spawnWithObject(6943, 2980, 3387, 0, 10, 1);//Banks MOVED
		spawnWithObject(6943, 3029, 3379, 0, 10, 1);//Banks MOVED
		
		//spawnWithObject(6249, 2996, 3383, 0, 10, 0);//Player owned shops - MOVED TO FALADOR
		
		//spawnWithObject(8720, 3005, 3383, 0, 10, 2);//vote booth MOVED
		deleteWithObject(3004,3383,0);
		setClipToZero(3004, 3383, 0);
		
		spawnWithObject(1751, 3008, 3388, 0, 10, 1);//Oak Tree
		spawnWithObject(1751, 3011, 3388, 0, 10, 1);//Oak Tree
		
		spawnWithObject(4674, 3015, 3388, 0, 10, 1);//Maple Tree
		spawnWithObject(4674, 3017, 3387, 0, 10, 1);//Maple Tree
		
		spawnWithObject(1753, 3001, 3388, 0, 10, 1);//Yew Tree
		spawnWithObject(1753, 3004, 3387, 0, 10, 1);//Yew Tree
		deleteWithObject(3026,3376,0);//hat stand
		
		
		
		//dzone
		spawnWithObject(6282, 2847, 5081, 0, 10, 2);//portal
		
		spawnWithObject(16604, 2844, 5086, 0, 10, 2);//dream tree
		spawnWithObject(16604, 2841, 5083, 0, 10, 2);//magic tree
		spawnWithObject(16604, 2841, 5079, 0, 10, 2);//magic tree
		spawnWithObject(16604, 2843, 5075, 0, 10, 2);//magic tree
		spawnWithObject(16604, 2850, 5075, 0, 10, 2);//magic tree
		spawnWithObject(16604, 2853, 5079, 0, 10, 2);//magic tree
		spawnWithObject(16604, 2853, 5083, 0, 10, 2);//magic tree
		spawnWithObject(16604, 2850, 5086, 0, 10, 2);//magic tree

		
		spawnWithObject(6943, 2851, 5089, 0, 10, 2);//Banks MOVED
		spawnWithObject(6943, 2844, 5089, 0, 10, 2);//Banks MOVED
		
		//end of dzone
		
		
		
		spawnWithObject(7457, 3021, 3368, 0, 10, 1); // Tin Ore Rocks
		spawnWithObject(7457, 3020, 3368, 0, 10, 1); // Tin Ore Rocks
		//spawnWithObject(7457, 3018, 3368, 0, 10, 1); // Tin Ore Rocks
		
		spawnWithObject(7454, 3019, 3368, 0, 10, 1); // Copper Ore Rocks
		spawnWithObject(7454, 3018, 3367, 0, 10, 1); // Copper Ore Rocks
		//spawnWithObject(7454, 3015, 3368, 0, 10, 1); // Copper Ore Rocks
		
		spawnWithObject(7455, 3017, 3367, 0, 10, 0);//Iron ore
		spawnWithObject(7455, 3016, 3367, 0, 10, 0);//Iron ore
		spawnWithObject(7455, 3015, 3367, 0, 10, 0);//Iron ore
		

		spawnWithObject(7456, 3014, 3367, 0, 10, 0);//Coal  ore
		spawnWithObject(7456, 3013, 3368, 0, 10, 0);//Coal  ore
		//spawnWithObject(7489, 3012, 3371, 0, 10, 0);//Coal  ore
		
		spawnWithObject(7491, 3012, 3369, 0, 10, 0);//Gold Ore
		spawnWithObject(7491, 3011, 3369, 0, 10, 0);//Gold Ore
		
		spawnWithObject(7459, 3010, 3369, 0, 10, 0);//mithril ore
		spawnWithObject(7459, 3009, 3369, 0, 10, 0);//mithril ore
		
		//spawnWithObject(7460, 3009, 3369, 0, 10, 0);//Adamant Ore
		spawnWithObject(7460, 3008, 3369, 0, 10, 0);//Adamant Ore
		
		//spawnWithObject(7489, 3007, 3370, 0, 10, 3);//Coal ore
		//spawnWithObject(7489, 3007, 3371, 0, 10, 3);//Coal ore
		//spawnWithObject(7489, 3008, 3372, 0, 10, 3);//Coal ore
		
		//spawnWithObject(7461, 3007, 3371, 0, 10, 3);//rune ore
		
		//spawnWithObject(2031, 2992, 3376, 0, 10, 3);//Anvil
		spawnWithObject(2031, 3028, 3375, 0, 10, 2);//Anvil
		
		//spawnWithObject(7457, 3007, 3389, 0, 10, 1); // Tin Ore Rocks BLOCKER MAGE TREE
		//spawnWithObject(7457, 3007, 3390, 0, 10, 1); // Tin Ore Rocks BLOCKER MAGE TREE
		//spawnWithObject(7457, 3007, 3391, 0, 10, 1); // Tin Ore Rocks BLOCKER MAGE TREE
		
		spawnWithObject(26181, 3030, 3383, 0, 10, -3);//cooking Range
		 
		
		deleteWithObject(3026,3077,0); //null
		deleteWithObject(2847,5087,0); //tables
		deleteWithObject(2848,5087,0); //tables
		deleteWithObject(2848,5088,0); //tables
		deleteWithObject(2847,5088,0); //tables
		deleteWithObject(2847,5088,0); //tables
		deleteWithObject(3021,3373,0); //tables
		deleteWithObject(3021,3374,0); //tables
		deleteWithObject(3019,3372,0); //tables
		deleteWithObject(3018,3372,0); //tables
		deleteWithObject(3029,3383,0); //tables
		deleteWithObject(3029,3382,0); //tables
		deleteWithObject(3030,3377,0); //tables
		deleteWithObject(3031,3376,0); //tables
		deleteWithObject(3030,3376,0); //tables
		deleteWithObject(3029,3376,0); //tables
		deleteWithObject(3031,3377,0); //tables
		deleteWithObject(2996,3390,0); //DELETED OBJECTS FOR TREES
//		deleteWithObject(2988,3390,0); //DELETED OBJECTS FOR TREES
		
		deleteWithObject(3017,3383,0); //FISHING POND
		deleteWithObject(3016,3383,0); //FISHING POND
		deleteWithObject(3016,3382,0); //FISHING POND
		deleteWithObject(3015,3382,0); //FISHING POND
		deleteWithObject(3014,3382,0); //FISHING POND
		deleteWithObject(3014,3381,0); //FISHING POND
		
		deleteWithObject(3021, 3384,0); //FISHING POND
		deleteWithObject(3022, 3384,0); //FISHING POND
		deleteWithObject(3023, 3383,0); //FISHING POND
		deleteWithObject(3018, 3371,0); //table
		deleteWithObject(3022, 3378,0); //FISHING POND
		deleteWithObject(3022, 3377,0); //FISHING POND
		deleteWithObject(3021, 3377,0); //FISHING POND
		deleteWithObject(3020, 3376,0); //FISHING POND
		
		
		deleteWithObject(3003,3372,0); //FLAX
		setClipToZero(3003,3372,0);
		
		setClipToZero(2999, 3375,0);
		
		//spawnWithObject(4309, 2998, 3374, 0, 10, 2);//Spinning Wheel
		//spawnWithObject(4309, 2999, 3374, 0, 10, 2);//Spinning Wheel
		
		//spawnWithObject(14896, 3005, 3372, 0, 10, 2);//FLAX
		//spawnWithObject(14896, 3005, 3373, 0, 10, 2);//FLAX
		//spawnWithObject(14896, 3005, 3374, 0, 10, 2);//FLAX
		//spawnWithObject(14896, 3004, 3374, 0, 10, 2);//FLAX
		//spawnWithObject(14896, 3004, 3373, 0, 10, 2);//FLAX
		//spawnWithObject(14896, 3004, 3372, 0, 10, 2);//FLAX
		//spawnWithObject(14896, 3003, 3372, 0, 10, 2);//FLAX
		//spawnWithObject(14896, 3003, 3373, 0, 10, 2);//FLAX
		//spawnWithObject(14896, 3003, 3374, 0, 10, 2);//FLAX
		
		spawnWithObject(1518, 3022, 3382, 0, 10, 0);//low level fishing spot 2
		spawnWithObject(1761, 3010, 3384, 0, 10, 0);//Magic Tree
		spawnWithObject(1761, 3013, 3384, 0, 10, 0);//Magic Tree
		spawnWithObject(1758, 3018, 3372, 0, 10, 3); //willow
		spawnWithObject(1758, 3021, 3374, 0, 10, 3); //willow
		spawnWithObject(1519, 3019, 3378, 0, 10, 3); //lobs
		spawnWithObject(14888, 3030, 3381, 0, 10, 3); //pottery MOVED
		spawnWithObject(6189, 3030, 3376, 0, 10, 2); //furnace MOVED
		spawnWithObject(4306, 2208, 3251, 0, 10, 1); //anvil
		spawnWithObject(4875, 2195, 3263, 0, 10, 0); // Stalls
		spawnWithObject(4876, 2198, 3263, 0, 10, 0); 
		spawnWithObject(4874, 2201, 3263, 0, 10, 0);
		spawnWithObject(4878, 2198, 3260, 0, 10, 0);
		spawnWithObject(4877, 2201, 3260, 0, 10, 0);
		//spawnWithObject(5162, 2194, 3261, 0, 10, 1); //thieveschest
		spawnWithObject(7134, 2208, 3261, 0, 10, 1); //flax
		spawnWithObject(7134, 2207, 3261, 0, 10, 1);
		spawnWithObject(7134, 2206, 3261, 0, 10, 1);
		spawnWithObject(7134, 2205, 3261, 0, 10, 1);
		spawnWithObject(5249, 2204, 3250, 0, 10, 0);

		
		
		
		/* Membership Area */
		spawnWithObject(6943, 2088, 3904, 0, 10, 0);//Banks
		spawnWithObject(6943, 2089, 3920, 0, 10, 1);//Banks
		spawnWithObject(6943, 2107, 3911, 0, 10, 0);//Banks
		spawnWithObject(6943, 2079, 3927, 0, 10, 0);//Banks
		spawnWithObject(6943, 2080, 3927, 0, 10, 0);//Banks
		spawnWithObject(6943, 2091, 3927, 0, 10, 0);//Banks
		spawnWithObject(6943, 2093, 3927, 0, 10, 0);//Banks
		spawnWithObject(6943, 2098, 3927, 0, 10, 0);//Banks
		spawnWithObject(6943, 2106, 3900, 0, 10, 1);//Banks
		spawnWithObject(409, 2069, 3911, 0, 10, 3);//prayer altar
		spawnWithObject(26820, 2104, 3918, 0, 10, 2);//vote booth
		spawnWithObject(9472, 2818, 3351, 0, 10, 5);//player owned shop
		spawnWithObject(6943, 2857, 3338, 0, 10, 0);//Banks
		spawnWithObject(6162, 2100, 3910, 0, 10, 1); //members stall
		spawnWithObject(4875, 2105, 3916, 0, 10, 5);//Food stall
		spawnWithObject(4876, 2104, 3916, 0, 10, 5);//General stall
		spawnWithObject(4874, 2103, 3916, 0, 10, 5);//Crafting stall
		spawnWithObject(4877, 2102, 3916, 0, 10, 5);//Magic stall
		spawnWithObject(4878, 2101, 3916, 0, 10, 5);//Scimtar stall
		spawnWithObject(26181, 2092, 3920, 0, 10, 2);//cooking Range
		spawnWithObject(4309, 2090, 3907, 0, 10, 1);//Spinning wheel
		spawnWithObject(11601, 2086, 3907, 0, 10, 0);//Pottery
		spawnWithObject(22472, 2097, 3917, 0, 10, 0);//Tab creation
		spawnWithObject(13618, 2094, 3918, 0, 10, 0);//Wyvern teleport
		spawnWithObject(13619, 2092, 3909, 0, 10, 0);//Fountain of rune teleport
		spawnWithObject(2191, 2098, 3916, 0, 10, 2);//Crystal chest
		spawnWithObject(18772, 2095, 3905, 0, 10, 2);//MysteryBox chest
		spawnWithObject(2097, 2102, 3908, 0, 10, 0);//Anvil
		spawnWithObject(2097, 2107, 3908, 0, 10, 2);//Anvil
		spawnWithObject(2030, 2103, 3905, 0, 10, 3);//furnace
		spawnWithObject(11764, 2102, 3929, 0, 10, 0);//Magic Tree
		spawnWithObject(11764, 2099, 3930, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2096, 3932, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2093, 3932, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2090, 3932, 0, 10, 1);//Magic Tree
		spawnWithObject(11764, 2087, 3932, 0, 10, 1);//Magic Tree
		spawnWithObject(11758, 2084, 3932, 0, 10, 1);//Yew Tree
		spawnWithObject(11758, 2081, 3932, 0, 10, 1);//Yew Tree
		spawnWithObject(11758, 2078, 3932, 0, 10, 1);//Yew Tree
		spawnWithObject(11762, 2075, 3932, 0, 10, 1);//Maple Tree
		spawnWithObject(11762, 2074, 3929, 0, 10, 1);//Maple Tree
		spawnWithObject(11762, 2077, 3928, 0, 10, 1);//Maple Tree
		spawnWithObject(11762, 2081, 3928, 0, 10, 1);//Maple Tree
		spawnWithObject(11762, 2084, 3928, 0, 10, 1);//Maple Tree
		spawnWithObject(7461, 2103, 3903, 0, 10, 1);//Rune Ore
		spawnWithObject(7461, 2104, 3903, 0, 10, 0);//Rune Ore
		spawnWithObject(7461, 2105, 3903, 0, 10, 1);//Rune Ore
		spawnWithObject(7461, 2106, 3903, 0, 10, 2);//Rune Ore
		spawnWithObject(7461, 2110, 3902, 0, 10, 1);//Rune Ore		
		spawnWithObject(7460, 2103, 3900, 0, 10, 0);//Adamant Ore
		spawnWithObject(7460, 2103, 3899, 0, 10, 1);//Adamant Ore
		spawnWithObject(7460, 2103, 3898, 0, 10, 0);//Adamant Ore
		spawnWithObject(7460, 2103, 3897, 0, 10, 1);//Adamant Ore
		spawnWithObject(7460, 2103, 3896, 0, 10, 0);//Adamant Ore		
		spawnWithObject(7491, 2109, 3900, 0, 10, 1);//Gold Ore
		spawnWithObject(7491, 2108, 3898, 0, 10, 0);//Gold Ore
		spawnWithObject(7492, 2107, 3897, 0, 10, 1);//mith Ore
		spawnWithObject(7492, 2106, 3896, 0, 10, 0);//mith Ore
		spawnWithObject(7456, 2102, 3900, 0, 10, 1);//Coal
		spawnWithObject(7456, 2101, 3900, 0, 10, 0);//Coal
		spawnWithObject(7456, 2100, 3900, 0, 10, 1);//Coal
		spawnWithObject(7456, 2102, 3903, 0, 10, 0);//Coal
		spawnWithObject(4090, 2095, 3924, 0, 10, 0);//blood Altar
		spawnWithObject(16604, 2086, 3913, 0, 10, 0);//Dream tree
		spawnWithObject(6282, 2108, 3925, 0, 10, 0);//boss portals
		deleteWithObject(2072, 3911, 0);
		deleteWithObject(2078, 3910, 0);
		deleteWithObject(2104, 3908, 0);
		deleteWithObject(2089, 3919, 0);
		deleteWithObject(2105, 3907, 0);
		deleteWithObject(2104, 3905, 0);
		deleteWithObject(2106, 3905, 0);
		deleteWithObject(2107, 3906, 0);
		deleteWithObject(2107, 3907, 0);
		deleteWithObject(2107, 3909, 0);
		deleteWithObject(2107, 3910, 0);
		deleteWithObject(2102, 3904, 0);
		deleteWithObject(2102, 3905, 0);
		deleteWithObject(2102, 3906, 0);
		deleteWithObject(2102, 3907, 0);
		deleteWithObject(2098, 3906, 0);
		deleteWithObject(2097, 3920, 0);
		deleteWithObject(2094, 3907, 0);
		deleteWithObject(2094, 3908, 0);
		deleteWithObject(2092, 3921, 0);
		deleteWithObject(2088, 3906, 0);
		deleteWithObject(2086, 3908, 0);
		deleteWithObject(2090, 3904, 0);
		deleteWithObject(2090, 3906, 0);
		deleteWithObject(2090, 3908, 0);
		deleteWithObject(2090, 3909, 0);
		deleteWithObject(2096, 3906, 0);
		deleteWithObject(2097, 3905, 0);
		deleteWithObject(2096, 3909, 0);
		deleteWithObject(2096, 3910, 0);
		deleteWithObject(2118, 3911, 0);
		deleteWithObject(2115, 3882, 0);
		deleteWithObject(2115, 3880, 0);
		deleteWithObject(2116, 3879, 0);
		deleteWithObject(2118, 3879, 0);
		deleteWithObject(2117, 3923, 0);
		deleteWithObject(2120, 3923, 0);
		deleteWithObject(2116, 3925, 0);
		deleteWithObject(2119, 3925, 0);
		deleteWithObject(2118, 3926, 0);
		deleteWithObject(2122, 3926, 0);
		deleteWithObject(2117, 3927, 0);
		deleteWithObject(2120, 3928, 0);
		deleteWithObject(2116, 3929, 0);
		deleteWithObject(2115, 3930, 0);
		deleteWithObject(2114, 3930, 0);
		setClipToZero(2543, 3171, 0);
		setClipToZero(2543, 3170, 0);
		setClipToZero(2543, 3169, 0);
		setClipToZero(2543, 3168, 0);
		setClipToZero(2542, 3171, 0);
		setClipToZero(2542, 3170, 0);
		setClipToZero(2542, 3169, 0);
		setClipToZero(2542, 3168, 0);
		setClipToZero(2541, 3171, 0);
		setClipToZero(2541, 3170, 0);
		setClipToZero(2541, 3169, 0);
		setClipToZero(2541, 3168, 0);
		setClipToZero(2540, 3171, 0);
		setClipToZero(2540, 3170, 0);
		setClipToZero(2540, 3169, 0);
		setClipToZero(2540, 3168, 0);
		setClipToZero(2528, 3163, 0);
		

		
		
		/* Wilderness Resource Arena */
		spawnWithObject(14175, 3195, 3942, 0, 10, 3);
		spawnWithObject(14175, 3194, 3943, 0, 10, 3);
		spawnWithObject(14175, 3175, 3937, 0, 10, 3);
		spawnWithObject(14175, 3175, 3943, 0, 10, 3);
		
		
		/* Woodcutting */
		spawnWithObject(11764, 3098, 3235, 0, 10, 0);//Magic Tree
		spawnWithObject(11764, 3095, 3233, 0, 10, 1);//Magic Tree
		spawnWithObject(11758, 3080, 3241, 0, 10, 1);//Yew Tree
		spawnWithObject(11758, 3077, 3240, 0, 10, 1);//Yew Tree
		spawnWithObject(11762, 3094, 3235, 0, 10, 1);//Maple Tree
		spawnWithObject(11762, 3091, 3237, 0, 10, 1);//Maple Tree
		spawnWithObject(9472, 3096, 3240, 0, 10, 5);//player owned shop
		deleteWithObject(3095, 3244, 0);
		deleteWithObject(3095, 3242, 0);
		
		/* Blood crafting */
		spawnWithObject(4090, 2792, 3322, 0, 10, 0);//Altar
	
		/* Crafting/Mining */
		spawnWithObject(4309, 2930, 3283, 0, 10, 3);//Spinning wheel
		spawnWithObject(11601, 2929, 3292, 0, 10, 1);//Pottery
		spawnWithObject(6943, 2936, 3284, 0, 10, 1);//Banks
		spawnWithObject(6943, 2936, 3281, 0, 10, 1);//Banks
		spawnWithObject(7134, 2927, 3289, 0, 10, 1); //flax
		spawnWithObject(7134, 2925, 3287, 0, 10, 1);
		spawnWithObject(7134, 2927, 3285, 0, 10, 1);
		spawnWithObject(7134, 2924, 3284, 0, 10, 1);
		spawnWithObject(7134, 2922, 3290, 0, 10, 1);
		spawnWithObject(7134, 2921, 3288, 0, 10, 1);
		spawnWithObject(7134, 2921, 3285, 0, 10, 1);
		spawnWithObject(13710, 2937, 3279, 0, 10, 1);
		spawnWithObject(13710, 2943, 3281, 0, 10, 1);
		spawnWithObject(13710, 2943, 3278, 0, 10, 1);
		spawnWithObject(13710, 2943, 3277, 0, 10, 1);
		spawnWithObject(13710, 2940, 3276, 0, 10, 1);
		spawnWithObject(13720, 2939, 3288, 0, 10, 1);
		spawnWithObject(13720, 2939, 3289, 0, 10, 1);
		deleteWithObject(2934, 3286, 0);
		deleteWithObject(2934, 3282, 0);
		deleteWithObject(2931, 3282, 0);
		deleteWithObject(2938, 3287, 0);
		deleteWithObject(2934, 3283, 0);
		
		/** crafting/mining */
		spawnWithObject(7461, 2940, 3291, 0, 10, 3);//rune ore
		spawnWithObject(7461, 2941, 3291, 0, 10, 3);//rune ore
		spawnWithObject(7456, 2943, 3289, 0, 10, 0);//Coal
		spawnWithObject(7456, 2943, 3288, 0, 10, 0);//Coal
		spawnWithObject(7456, 2943, 3287, 0, 10, 0);//Coal
		spawnWithObject(7460, 2941, 3279, 0, 10, 0);//Adamant Ore
		spawnWithObject(7460, 2940, 3278, 0, 10, 0);//Adamant Ore
		spawnWithObject(7455, 2940, 3276, 0, 10, 0);//Iron ore
		spawnWithObject(7455, 2938, 3277, 0, 10, 0);//Iron ore
		spawnWithObject(7455, 2938, 3279, 0, 10, 0);//Iron ore
		spawnWithObject(7459, 2939, 3289, 0, 10, 0);//mithril ore
		spawnWithObject(7459, 2939, 3288, 0, 10, 0);//mithril ore
		spawnWithObject(9472, 2936, 3279, 0, 10, 5);//player owned shop
		
		/** custom kraken boss in a bowl */
		setClipToZero(3684, 9889, 0); //stairs
		
		
		/** Smithing  */
		spawnWithObject(2030, 3191, 3425, 0, 10, 0);
		spawnWithObject(6943, 3682, 3479, 0, 10, 0);//Banks
		spawnWithObject(2097, 3684, 3481, 0, 10, 1);//Anvil
		spawnWithObject(9472, 3683, 3476, 0, 10, 5);//player owned shop
		spawnWithObject(26820, 3685, 3476, 0, 10, 0);//vote booth
		setClipToZero(3687, 3479, 0);
		
		/** Weapon Game **/
		deleteWithObject(1863, 5328, 0);
		deleteWithObject(1863, 5326, 0);
		deleteWithObject(1863, 5323, 0);
		deleteWithObject(1862, 5327, 0);
		deleteWithObject(1862, 5326, 0);
		deleteWithObject(1862, 5325, 0);
		deleteWithObject(1865, 5325, 0);
		deleteWithObject(1863, 5321, 0);
		deleteWithObject(1865, 5321, 0);
		deleteWithObject(1865, 5323, 0);
		deleteWithObject(1863, 5319, 0);
		deleteWithObject(1862, 5319, 0);
		deleteWithObject(1863, 5317, 0);
		deleteWithObject(1865, 5319, 0);
		deleteWithObject(1862, 5321, 0);
		deleteWithObject(1862, 5323, 0);
		spawnWithObject(1, 1866, 5323, 0, 10, 0);//Barrier	
		spawnWithObject(1, 1865, 5323, 0, 10, 0);//Barrier	
		spawnWithObject(11005, 1864, 5323, 0, 10, 1);//Barrier	
		spawnWithObject(11005, 1863, 5323, 0, 10, 1);//Barrier	
		spawnWithObject(1, 1862, 5323, 0, 10, 0);//Barrier	
		spawnWithObject(1, 1861, 5323, 0, 10, 0);//Barrier	
		spawnWithObject(6943, 1861, 5330, 0, 10, 0);//Barrier	
		spawnWithObject(6943, 1862, 5330, 0, 10, 0);//Barrier	
		spawnWithObject(6943, 1863, 5330, 0, 10, 0);//Barrier	
		spawnWithObject(6943, 1864, 5330, 0, 10, 0);//Barrier
		spawnWithObject(6943, 1865, 5330, 0, 10, 0);//Barrier
		spawnWithObject(6943, 1866, 5330, 0, 10, 0);//Barrier
		
		/** Duel Arena */
		spawnWithObject(409, 3366, 3271, 0, 10, 10);//Altar	
		spawnWithObject(6552, 3370, 3271, 0, 10, 10);//Ancient Altar
		
		/* Crafting Area */
		spawnWithObject(6943, 2748, 3451, 0, 10, 0);// Banks
		
		/** Farming Areas */
		spawnWithObject(6943, 2804, 3463, 0, 10, 1);// Catherby Banks
		spawnWithObject(6943, 3599, 3522, 0, 10, 0);// Banks
		spawnWithObject(6943, 3056, 3311, 0, 10, 0);// Banks
		spawnWithObject(6943, 2662, 3375, 0, 10, 0);// Banks

		/** Mining banks */
		spawnWithObject(6943, 3047, 9765, 0, 10, 0);
		spawnWithObject(6943, 3045, 9765, 0, 10, 0);
		spawnWithObject(6943, 3044, 9776, 0, 10, 0);
		spawnWithObject(6943, 3045, 9776, 0, 10, 0);
		spawnWithObject(6943, 3046, 9776, 0, 10, 0);
		spawnWithObject(6943, 2930, 4821, 0, 10, 0);// Essences
		
		/** wilderness agility course teleport */
		spawnWithObject(7272, 2998, 3913, 0, 10, 0);
		
		
		/** Deleting Objects */
		delete(3079, 3501, 0);//Home gate
		delete(3080, 3501, 0);//Home gate
		delete(3445, 3554, 2);//Slayer tower door
		deleteWithObject(3248, 9364, 0);
		
		/** Corrupt Sorceress */
		deleteWithObject(2388, 9813, 0);
		deleteWithObject(2387, 9826, 0);
		deleteWithObject(2392, 3958, 0);
		setClipToZero(2388, 9817, 0);
		setClipToZero(2390, 9816, 0);
		setClipToZero(2391, 9814, 0);
		setClipToZero(2391, 9813, 0);
		setClipToZero(2391, 9812, 0);
		setClipToZero(2390, 9810, 0);
		setClipToZero(2386, 9810, 0);
		setClipToZero(2385, 9814, 0);
		setClipToZero(2385, 9813, 0);
		setClipToZero(2385, 9812, 0);
		setClipToZero(2386, 9816, 0);
		
		
		
		
		
		/** Webs */
		delete(3105, 3958, 0);
		delete(3106, 3958, 0);
		delete(3093, 3957, 0);
		deleteWithObject(3095, 3957, 0);
		delete(3092, 3957, 0);
		delete(3158, 3951, 0);
		deleteWithObject(2543, 4715, 0);
		spawnWithObject(734, 3105, 3958, 0, 10, 3);
		spawnWithObject(734, 3106, 3958, 0, 10, 3);
		spawnWithObject(734, 3158, 3951, 0, 10, 1);
		spawnWithObject(734, 3093, 3957, 0, 10, 0);
		spawnWithObject(734, 3095, 3957, 0, 10, 0);	
		delete(2543, 4715, 0);	
		delete(2855, 3546, 0);
		delete(2854, 3546, 0);
		deleteWithObject(2540, 3172, 0);
		deleteWithObject(2540, 3173, 0);
		deleteWithObject(2540, 3174, 0);
		deleteWithObject(2540, 3175, 0);
		deleteWithObject(2540, 3169, 0);
		setClipToZero(2540, 3169, 0);
		deleteWithObject(2540, 3165, 0);
		setClipToZero(2540, 3165, 0);
		deleteWithObject(2540, 3162, 0);
		setClipToZero(2540, 3162, 0);
		deleteWithObject(2541, 3165, 0);
		deleteWithObject(2541, 3159, 0);
		deleteWithObject(2539, 3159, 0);
		setClipToZero(2539, 3159, 0);
		deleteWithObject(2539, 3174, 0);
		deleteWithObject(2538, 3172, 0);
		setClipToZero(2538, 3172, 0);
		deleteWithObject(2538, 3166, 0);
		setClipToZero(2538, 3166, 0);
		deleteWithObject(2537, 3162, 0);
		deleteWithObject(2537, 3168, 0);
		deleteWithObject(2536, 3174, 0);
		deleteWithObject(2535, 3159, 0);
		setClipToZero(2535, 3159, 0);
		deleteWithObject(2535, 3164, 0);
		setClipToZero(2535, 3164, 0);
		deleteWithObject(2535, 3167, 0);
		setClipToZero(2535, 3167, 0);
		deleteWithObject(2535, 3172, 0);
		setClipToZero(2535, 3172, 0);
		deleteWithObject(3209, 3492, 0);
		deleteWithObject(3213, 3482, 0);
		deleteWithObject(3500, 3487, 0);
		deleteWithObject(3489, 3502, 0);
		deleteWithObject(3488, 3502, 0);
		deleteWithObject(3492, 3504, 0);
		deleteWithObject(3487, 3499, 0);
		deleteWithObject(3485, 3499, 0);
		deleteWithObject(3489, 3505, 0);
		deleteWithObject(3490, 3505, 0);
		deleteWithObject(3493, 3504, 0);
		deleteWithObject(3491, 3504, 0);
		deleteWithObject(3509, 3477, 0);
		deleteWithObject(3514, 3476, 0);
		deleteWithObject(3510, 3481, 0);
		deleteWithObject(3478, 3493, 0);
		deleteWithObject(3475, 3492, 0);
		deleteWithObject(3478, 3500, 0);
		deleteWithObject(3476, 3498, 0);
		deleteWithObject(3476, 3494, 0);
		deleteWithObject(3476, 3492, 0);
		deleteWithObject(3503, 3484, 0);
		deleteWithObject(3504, 3479, 0);
		deleteWithObject(3502, 3486, 0);
		deleteWithObject(3498, 3482, 0);
		deleteWithObject(3504, 3481, 0);
		deleteWithObject(3497, 3499, 0);
		deleteWithObject(3497, 3498, 0);
		deleteWithObject(3498, 3499, 0);
		deleteWithObject(3489, 3480, 0);
		setClipToZero(2540, 3172, 0);
		deleteWithObject(2541, 3174, 0);
		deleteWithObject(2534, 3174, 0);
		deleteWithObject(2534, 3161, 0);
		deleteWithObject(2532, 3174, 0);
		deleteWithObject(2532, 3162, 0);
		deleteWithObject(2532, 3160, 0);
		setClipToZero(2540, 3174, 0);
		deleteWithObject(2531, 3158, 0);
		deleteWithObject(2531, 3166, 0);
		deleteWithObject(2531, 3168, 0);
		deleteWithObject(2530, 3174, 0);
		deleteWithObject(2529, 3158, 0);
		deleteWithObject(2529, 3166, 0);
		deleteWithObject(2529, 3170, 0);
		deleteWithObject(2528, 3174, 0);
		deleteWithObject(2528, 3172, 0);
		setClipToZero(2528, 3172, 0);
		deleteWithObject(2528, 3168, 0);
		deleteWithObject(2528, 3163, 0);
		deleteWithObject(2528, 3158, 0);
		deleteWithObject(2527, 3165, 0);
		deleteWithObject(2527, 3166, 0);
		deleteWithObject(2526, 3174, 0);
		deleteWithObject(2526, 3169, 0);
		deleteWithObject(2526, 3166, 0);
		deleteWithObject(2526, 3158, 0);
		deleteWithObject(2525, 3162, 0);
		deleteWithObject(2525, 3172, 0);
		deleteWithObject(2524, 3165, 0);
		deleteWithObject(2524, 3158, 0);
		deleteWithObject(2523, 3174, 0);
		deleteWithObject(2522, 3169, 0);
		deleteWithObject(2522, 3166, 0);
		deleteWithObject(2522, 3160, 0);
		deleteWithObject(2521, 3162, 0);
		deleteWithObject(2521, 3164, 0);
		setClipToZero(2521, 3164, 0);
		deleteWithObject(2519, 3167, 0);
		deleteWithObject(2519, 3173, 0);
		deleteWithObject(2518, 3169, 0);
		deleteWithObject(2518, 3162, 0);
		setClipToZero(2518, 3162, 0);
		deleteWithObject(2516, 3168, 0);
		deleteWithObject(2516, 3165, 0);
		deleteWithObject(2514, 3163, 0);
		deleteWithObject(2514, 3168, 0);
		setClipToZero(2514, 3168, 0);
		deleteWithObject(2514, 3169, 0);
		deleteWithObject(2515, 3173, 0);
		deleteWithObject(2514, 3174, 0);
		deleteWithObject(2531, 3169, 0);
		deleteWithObject(2524, 3164, 0);
		deleteWithObject(2543, 3168, 0);
		setClipToZero(2544, 3168, 0);
		setClipToZero(2544, 3169, 0);
		setClipToZero(2544, 3170, 0);
		setClipToZero(2544, 3171, 0);
		setClipToZero(2545, 3168, 0);
		setClipToZero(2545, 3169, 0);
		setClipToZero(2545, 3170, 0);
		setClipToZero(2545, 3171, 0);
		setClipToZero(2546, 3168, 0);
		setClipToZero(2546, 3169, 0);
		setClipToZero(2546, 3170, 0);
		setClipToZero(2546, 3171, 0);
		setClipToZero(2543, 3168, 0);
		setClipToZero(2543, 3169, 0);
		setClipToZero(2543, 3170, 0);
		setClipToZero(2543, 3171, 0);
		setClipToZero(2528, 3163, 0);
		setClipToZero(2100, 3916, 0);
		setClipToZero(2099, 3916, 0);
		
		/** Clipping */
		setClipToZero(3445, 3554, 2);
		setClipToZero(3119, 9850, 0);
		setClipToZero(3002, 3961, 0);
		setClipToZero(3002, 3960, 0);
		setClipToZero(2539, 4716, 0);
		//setClipToZero(3068, 10255, 0);
		setClipToZero(3068, 10256, 0);
		setClipToZero(3068, 10258, 0);
		setClipToZero(3067, 10255, 0);
		setClipToZero(3066, 10256, 0);
		setClipToZero(3426, 3555, 1);
		setClipToZero(3427, 3555, 1);
		setClipToZero(3005, 3953, 0);
		setClipToZero(3005, 3952, 0);
		setClipToZero(2551, 3554, 0);
		setClipToZero(2551, 3555, 0);
		setClipToZero(2833, 3352, 0);
		setClipToZero(2996, 3960, 0);
		setClipToZero(3687, 3471, 0);
		spawnWithObject(1, 3687, 3472,0, 10, 0);
		setClipToZero(3688, 3470, 0);
		setClipToZero(3687, 3467, 0);
		setClipToZero(3695, 3470, 0);
		setClipToZero(3698, 3469, 0);
		setClipToZero(3698, 3471, 0);
		setClipToZero(3697, 3462, 0);
		setClipToZero(3698, 3462, 0);
		setClipToZero(3502, 3480, 0);
		setClipToZero(3502, 3479, 0);
		setClipToZero(3501, 3480, 0);
		setClipToZero(3501, 3479, 0);
		setClipToZero(3500, 3480, 0);
		setClipToZero(3500, 3479, 0);
		setClipToZero(3499, 3480, 0);
		setClipToZero(3499, 3479, 0);
		//setClipToZero(3498, 3480, 0);
		//setClipToZero(3498, 3479, 0);
		setClipToZero(3504, 3480, 0);
		setClipToZero(3504, 3479, 0);
		setClipToZero(3503, 3480, 0);
		setClipToZero(3503, 3479, 0);
		setClipToZero(3505, 3479, 0);
		setClipToZero(3505, 3478, 0);
		
		for (GameObject i : active) {
			send(i);
		}

		logger.info("All object spawns have been loaded successfully.");
	}
	
	private static Logger logger = Logger.getLogger(MapLoading.class.getSimpleName());

	private static final void delete(int x, int y, int z) {
		RSObject object = Region.getObject(x, y, z);

		if (Region.getDoor(x, y, z) != null) {
			Region.removeDoor(x, y, z);
		}

		if (object == null) {
			if (z > 0)
				active.add(new GameObject(2376, x, y, z, 10, 0));
			return;
		}

		MapLoading.removeObject(object.getId(), x, y, z, object.getType(), object.getFace());

		if ((object.getType() != 10) || (z > 0))
			active.add(new GameObject(2376, x, y, z, object.getType(), 0));
	}

	private static final void deleteWithObject(int x, int y, int z) {
		RSObject object = Region.getObject(x, y, z);

		if (Region.getDoor(x, y, z) != null) {
			Region.removeDoor(x, y, z);
		}

		if (object == null) {
			active.add(new GameObject(2376, x, y, z, 10, 0));
			return;
		}

		MapLoading.removeObject(object.getId(), x, y, z, object.getType(), object.getFace());

		active.add(new GameObject(2376, x, y, z, object.getType(), 0));
	}
	
	private static final void remove(int x, int y, int z) {
		RSObject object = Region.getObject(x, y, z);
		
		if (Region.getDoor(x, y, z) != null) {
			Region.removeDoor(x, y, z);
		}
		
		if (object == null) {
			active.add(new GameObject(2376, x, y, z, 10, 0));
			return;
		}
		
		MapLoading.removeObject(object.getId(), x, y, z, object.getType(), object.getFace());
		
		active.add(new GameObject(2376, x, y, z, object.getType(), 0));
		Region region = Region.getRegion(x, y);

		region.setClipToZero(x, y, z);
	}
	

	private static final void deleteWithObject(int x, int y, int z, int type) {
		active.add(new GameObject(2376, x, y, z, type, 0));
	}

	public static List<GameObject> getActive() {
		return active;
	}

	public static final GameObject getBlankObject(Location p) {
		return new GameObject(2376, p.getX(), p.getY(), p.getZ(), 10, 0, false);
	}

	public static GameObject getBlankObject(Location p, int type) {
		return new GameObject(2376, p.getX(), p.getY(), p.getZ(), type, 0, false);
	}

	public static GameObject getGameObject(int x, int y, int z) {
		int index = active.indexOf(new GameObject(x, y, z));

		if (index == -1) {
			return null;
		}

		return active.get(index);
	}

	public static Queue<GameObject> getSend() {
		return send;
	}

	public static boolean objectExists(Location location) {
		for (GameObject object : active) {
			if (location.equals(object.getLocation())) {
				return true;
			}
		}
		return false;
	}

	public static void process() {
		for (Iterator<GameObject> i = register.iterator(); i.hasNext();) {
			GameObject reg = i.next();
			active.remove(reg);
			active.add(reg);
			send.add(reg);

			i.remove();
		}
	}

	public static void queueSend(GameObject o) {
		send.add(o);
	}

	public static void register(GameObject o) {
		register.add(o);
	}

	public static void remove(GameObject o) {
		removeFromList(o);
		send.add(getBlankObject(o.getLocation(), o.getType()));
	}

	public static void remove2(GameObject o) {
		send.add(getBlankObject(o.getLocation(), o.getType()));
	}
	

	public static void removeFromList(GameObject o) {
		active.remove(o);
	}

	private static final void removeWithoutClip(int x, int y, int z, int type) {
	}

	public static void send(GameObject o) {
		for (Player player : World.getPlayers())
			if ((player != null) && (player.isActive())) {
				if ((player.withinRegion(o.getLocation())) && (player.getLocation().getZ() % 4 == o.getLocation().getZ() % 4))
					player.getObjects().add(o);
			}
	}

	public static void setClipToZero(int x, int y, int z) {
		Region region = Region.getRegion(x, y);

		region.setClipToZero(x, y, z);
	}

	public static void setClipped(int x, int y, int z) {
		Region region = Region.getRegion(x, y);

		region.setClipping(x, y, z, 0x12801ff);
	}

	public static void setProjecileClipToInfinity(int x, int y, int z) {
		Region region = Region.getRegion(x, y);

		region.setProjecileClipToInfinity(x, y, z);
	}

	private static final void spawn(int id, int x, int y, int z, int type, int face) {
		MapLoading.addObject(false, id, x, y, z, type, face);
	}
	
	public static final void spawnWithObject(int id, Location location, int type, int face) {
		active.add(new GameObject(id, location.getX(), location.getY(), location.getZ(), type, face));
		MapLoading.addObject(false, id, location.getX(), location.getY(), location.getZ(), type, face);

		send(new GameObject(id, location.getX(), location.getY(), location.getZ(), type, face));
	}

	public static final void spawnWithObject(int id, int x, int y, int z, int type, int face) {
		active.add(new GameObject(id, x, y, z, type, face));
		MapLoading.addObject(false, id, x, y, z, type, face);

		send(new GameObject(id, x, y, z, type, face));
		register(new GameObject(id, x, y, z, type, face));
	}
	
}
