package com.vencillio;

/**
 * The game settings for the server
 * 
 * @author Vencillio Team
 */
public class VencillioConstants {

	/**
	 * The version of Vencillio
	 */
	public static final double VERSION = 1.0;
	
	/**
	 * Checks if in development mode
	 */
	public static boolean DEV_MODE = false;

	/**
	 * Walking check
	 */
	public static boolean WALK_CHECK = true;
	
	/**
	 * Checks if the world is staff only
	 */
	public static boolean STAFF_ONLY = false;
	
	/**
	 * Check to see if double experience is enabled
	 */
	public static final boolean doubleExperience = false;

	/**
	 * Current amount of votes
	 */
	public static int CURRENT_VOTES = 0;

	/**
	 * The last voter
	 */
	public static String LAST_VOTER = "None";

	/**
	 * Holds the most players online at once
	 */
	public static int MOST_ONLINE = 0;	

	/**
	 * Strings that are classified as bad
	 */
	public static final String[] BAD_STRINGS = { 
		"fag", "f4g", "faggot", "nigger", "fuck", "bitch", "whore", 
		"slut", "gay", "lesbian", "scape", ".net", ".com", ".org", 
		"vagina", "dick", "cock", "penis", "hoe", "soulsplit", "ikov", 
		"retard", "cunt", "poop", "#saints", "saints", "541n75", "s4ints", 
		"sa1nts"
	};
	
	/**
	 * Holds all usernames that can not be used
	 */
	public static final String[] BAD_USERNAMES = { 
		"admin", "moderator", "administrator", "owner", "m0d", "adm1n", "0wner", 
		"retard", "Nigga", "nigger", "n1gger", "n1gg3r", "nigg3r", "n1gga", "cock", 
		"faggot", "fag", "anus", "arse", "fuck", "bastard", "bitch", "cunt", "chode", 
		"damn", "dick", "faggit", "gay", "homo", "jizz", "lesbian", "negro", "pussy", "penis",
		"queef", "twat", "titty", "whore", "b1tch", "saints", "poop", "#saints", "541n75", "s4ints", 
		"sa1nts", "mod"
	};

	/**
	 * Strings that may not be used for yelltitles
	 */
	public static final String[] BAD_TITLES = { 
		"owner", "0wner", "own3r", "0wn3r", "admin", "administrator", "dev",
		"developer", "m0d", "moderator", "m0derator", 
	};
	
	/**
	 * All the staff members
	 */
	public final static String[] STAFF_MEMBERS = { 
		"Daniel", "Nate", "Boop"
	};
	
	/**
	 * Login Messages for players
	 */
	public static final String[] LOGIN_MESSAGES = { 
		"There are currently@dre@ /s/ </col>players online.",
		"Be sure to vote every 12 hours for more players!",
	};
	
	/**
	 * Messages for identifying items in the DropTable interface
	 */
	public static final String[] ITEM_IDENTIFICATION_MESSAGES = { 
		"I would sell my left kidney for /s/!",
		"Who needs a girlfriend when you have /s/? Now only if I had one...",
		"God! I wish I owned /s/!",
		"Someone please give me /s/.",
		"My only dream in life is to obtain /s/!",
		"If I believe hard enough, Dan will give me /s/! JK.",
		"Please... Please god... Give me /s/!",
	};
	
	/**
	 * Item dismantling data
	 */
	public static final int[][] ITEM_DISMANTLE_DATA = {
		{ 12436, 6585, 12526 }, //Amulet of fury (or)
		{ 12807, 11926, 12802 }, //Odium ward (or)
		{ 12806, 11924, 12802 }, //Malediction ward (or)
		{ 12797, 11920, 12800 }, //Dragon pickaxe (or)
		{ 12414, 2513, 12534 }, //Dragon chain (g) 1
		{ 12414, 3140, 12534 }, //Dragon chain (g) 2
		{ 12417, 11335, 12538 }, //Dragon fullhelm (g)
		{ 12415, 4087, 12536 }, //Dragon platelegs (g)
		{ 12416, 4585, 12536 }, //Dragon skirt (g)
		{ 12795, 11787, 12798 }, //Steam battlestaff
		{ 12796, 11789, 12798 }, //Mystic steam battlestaff
		{ 12809, 11838, 12804 }, //Blessed saradomin sword
		{ 12006, 4151, 12004 }, //Tentacle whip
		{ 12848, 4153, 12849 }, //Granite maul (or)
		{ 12785, 2572, 12783 }, //Ring of wealth (i)
	};
	
	/**
	 * Holds all doors that can not be opened
	 */
	public static final int[] BLOCKED_DOORS = {
		26502, 26503, 26504, 26505, 24306, 24309, 26760, 2104, 
		2102, 2100, 26461, 4799, 16902, 24368, 24363,
	};
	
}
