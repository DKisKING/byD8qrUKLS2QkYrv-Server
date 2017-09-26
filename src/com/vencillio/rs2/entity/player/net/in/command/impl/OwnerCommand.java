package com.vencillio.rs2.entity.player.net.in.command.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.sun.security.ntlm.Client;
import com.vencillio.core.cache.map.ObjectDef;
import com.vencillio.core.definitions.ItemDefinition;
import com.vencillio.core.definitions.NpcDefinition;
import com.vencillio.core.util.GameDefinitionLoader;
import com.vencillio.core.util.Utility;
import com.vencillio.rs2.content.DropTable;
import com.vencillio.rs2.content.bank.Bank;
import com.vencillio.rs2.content.combat.Hit;
import com.vencillio.rs2.content.combat.Hit.HitTypes;
import com.vencillio.rs2.content.dialogue.DialogueManager;
import com.vencillio.rs2.content.gambling.Gambling;
import com.vencillio.rs2.content.gambling.Lottery;
import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.content.interfaces.impl.QuestTab;
import com.vencillio.rs2.content.io.PlayerSave;
import com.vencillio.rs2.content.membership.RankHandler;
import com.vencillio.rs2.content.skill.Skill;
import com.vencillio.rs2.content.skill.Skills;
import com.vencillio.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.vencillio.rs2.entity.Location;
import com.vencillio.rs2.entity.World;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.item.impl.GroundItemHandler;
import com.vencillio.rs2.entity.mob.Mob;
import com.vencillio.rs2.entity.object.GameObject;
import com.vencillio.rs2.entity.object.ObjectManager;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.PlayerConstants;
import com.vencillio.rs2.entity.player.net.in.command.Command;
import com.vencillio.rs2.entity.player.net.in.command.CommandParser;
import com.vencillio.rs2.entity.player.net.out.impl.SendBanner;
import com.vencillio.rs2.entity.player.net.out.impl.SendInterface;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;
import com.vencillio.rs2.entity.player.net.out.impl.SendString;


public class OwnerCommand implements Command {

	@Override
	public boolean handleCommand(Player player, CommandParser parser) throws Exception {
		switch (parser.getCommand()) {
		
		/**
		 * Daniel's testing command
		 */
		case "bang":
			for (int i = 0; i < 4; i++) {
				player.hit(new Hit(10, HitTypes.MONEY));				
			}
			return true;
			
		 	case "spawn":
	        	DialogueManager.sendStatement(player, "@red@Quick-Spawn commands</col>", "::spawnmelee | ::spawnmelee2 | ::spawnbrid | ::spawnpure", "::spawnfood | ::spawnrunes | ::spawnarrows | ::spawnpotions");
	        	return true;
	            
	        case "spawnmelee":
	            player.getInventory().add(4716, 1);
	            player.getInventory().add(4718, 1);
	            player.getInventory().add(4720, 1);
	            player.getInventory().add(4722, 1);
	            player.getInventory().add(4151, 1);
	            player.getInventory().add(12954, 1);
	            player.getInventory().add(11840, 1);
	            player.getInventory().add(6570, 1);
	            player.getInventory().add(7462, 1);
	            player.getInventory().add(6737, 1);
	            player.getInventory().add(6585, 1);
	            return true;
	            
	        case "spawnmelee2":
	        	 player.getInventory().add(10828, 1);
	        	 player.getInventory().add(10551, 1);
	        	 player.getInventory().add(4087, 1);
	        	 player.getInventory().add(4151, 1);
	        	 player.getInventory().add(5698, 1);
	             player.getInventory().add(12954, 1);
	             player.getInventory().add(11840, 1);
	             player.getInventory().add(6570, 1);
	             player.getInventory().add(7462, 1);
	             player.getInventory().add(6737, 1);
	             player.getInventory().add(6585, 1);
	             return true;
	             
	        case "spawnbrid":
	        	player.getInventory().add(2414, 1);
	        	player.getInventory().add(4675, 1);
	        	player.getInventory().add(5698, 1);
	        	player.getInventory().add(12954, 1);
	        	player.getInventory().add(4712, 1);
	        	player.getInventory().add(4736, 1);
	        	player.getInventory().add(9185, 1);
	        	player.getInventory().add(10828, 1);
	        	player.getInventory().add(4714, 1);
	        	player.getInventory().add(4738, 1);
	        	player.getInventory().add(6570, 1);
	        	player.getInventory().add(6585, 1);
	        	player.getInventory().add(6920, 1);
	        	player.getInventory().add(10499, 1);
	        	player.getInventory().add(9244, 150);
	        	return true;
	        	
	        case "spawnpure":
	        	player.getInventory().add(9185, 1);
	        	player.getInventory().add(2497, 1);
	        	player.getInventory().add(4675, 1);
	        	player.getInventory().add(8950, 1);
	        	player.getInventory().add(10499, 1);
	        	player.getInventory().add(9244, 150);
	        	player.getInventory().add(3842, 1);
	        	player.getInventory().add(6107, 1);
	        	player.getInventory().add(4151, 1);
	        	player.getInventory().add(5698, 1);
	        	player.getInventory().add(2414, 1);
	        	player.getInventory().add(6108, 1);
	        	player.getInventory().add(6570, 1);
	        	player.getInventory().add(3105, 1);
	        	player.getInventory().add(7459, 1);
	        	player.getInventory().add(6585, 1);
	        	player.getInventory().add(6737, 1);
	        	player.getInventory().add(6731, 1);
	        	return true;
	        	
	        case "spawnfood":
	        	player.getInventory().add(380, 1000);
	        	player.getInventory().add(386, 1000);
	        	player.getInventory().add(392, 1000);
	        	player.getInventory().add(7061, 1000);
	        	player.getInventory().add(11937, 1000);
	        	player.getInventory().add(3145, 1000);
	        	return true;
	        	
	        case "spawnpotions":
	        	player.getInventory().add(2437, 100);
	        	player.getInventory().add(2441, 100);
	        	player.getInventory().add(2443, 100);
	        	player.getInventory().add(2445, 100);
	        	player.getInventory().add(3041, 100);
	        	player.getInventory().add(3025, 100);
	        	player.getInventory().add(2435, 100);
	        	player.getInventory().add(6686, 1);
	        	return true;
	        	
	        case "spawnrunes":
	        	player.getInventory().add(560, 4000);
	        	player.getInventory().add(565, 2000);
	        	player.getInventory().add(555, 6000);
	        	player.getInventory().add(9075, 4000);
	        	player.getInventory().add(560, 2000);
	        	player.getInventory().add(557, 10000);
	        	return true;
	        	
	        case "spawnarrows":
	        	player.getInventory().add(892, 500);
	        	player.getInventory().add(11212, 500);
	        	player.getInventory().add(9244, 500);
	        	player.getInventory().add(9245, 500);
	        	player.getInventory().add(9243, 500);
	        	player.getInventory().add(9242, 500);
	        	return true;
	        	
	        	
			
		case "obj":
		case "object":
			if (parser.hasNext()) {
				int id = parser.nextInt();
				int face = 0;

				if (parser.hasNext()) {
					face = parser.nextInt();

					if (face > 3) {
						face = 3;
					}

					if (face < 0) {
						face = 0;
					}
				}

				ObjectManager.addClippedObject(new GameObject(id, player.getLocation(), 10, face));

				player.send(new SendMessage("Spawned object \'" + ObjectDef.getObjectDef(id).name + "\' at " + player.getLocation() + " facing " + face));
			}
			return true;
		
		/**
		 * Gamble data
		 */
		case "gambledata":
			DialogueManager.sendStatement(player, "@blu@" + Utility.format(Gambling.MONEY_TRACKER));
			return true;
		

		case "give":
            if (parser.hasNext()) {
                String item = parser.nextString();
                int amount = 1;

                if (parser.hasNext()) {
                    amount = Integer.parseInt(parser.nextString().toLowerCase().replace("k", "000").replace("m", "000000").replace("b", "000000000"));
                }

                player.getBank().clear();

                List<ItemDefinition> items = (List<ItemDefinition>) GameDefinitionLoader.getItemDefinitions().values().stream().filter(def -> !def.isNote() && def.getName().toLowerCase().contains(item.replace("_", " "))).collect(Collectors.toList());
    			
                int added = 0;
                for (ItemDefinition def: items) {
                    if (added < Bank.SIZE) {
                        player.getBank().depositFromNoting(def.getId(), amount, 0, false);
                        added++;
                    }
                }

                items.clear();

                player.getBank().update();
                player.getBank().openBank();
                player.send(new SendMessage("Added @red@" + Utility.format(added) + "</col> of items with keywords: @red@" + item + "</col> to your bank."));
            }
            return true;

			
        case "shops":
        case "shop":
        case "shopping":
        case "stores":
            if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You cannot teleport above 20 wilderness!"));
                return true;
            }
            player.getMagic().teleport(1641, 3673, 0, TeleportTypes.SPELL_BOOK);
            return true;
			
			
		/**
		 * Does a force draw of the lottery
		 */

		case "forcedraw":
			Lottery.draw();
			return true;
			
		/**
		 * Yells the lottery status
		 */
		case "announcelottery":
		case "yelllottery":
			Lottery.announce();
			return true;
		
		/**
		 * Mass scare
		 */
		case "massboo":
		case "massscare":
			for (Player players : World.getPlayers()) {
				if (players != null && players.isActive()) {
					players.send(new SendInterface(18681));
				}
			}
			player.send(new SendMessage("Mass Boo activated"));
			return true;
		
		/**
		 * Forces message to player
		 */
		case "forcemsg":
			if (parser.hasNext(2)) {
				try {
					String name = parser.nextString();
					String msg = parser.nextString().replaceAll("_", " ");
					Player p = World.getPlayerByName(name);
					if (p == null) {
						player.send(new SendMessage("Player not found."));
					}
					p.getUpdateFlags().sendForceMessage(Utility.formatPlayerName(msg));
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
				}
			}
			return true;
			
			/*
			 * Teleports everyone to dude
			 */
		case "teleall":
		case "alltome":
			for (Player players : World.getPlayers()) {
				if (players != null && players.isActive()) {
					if (players != player) {
						players.teleport(player.getLocation());
						players.send(new SendMessage("<col=1C889E>You have been teleported to " + player.determineIcon(player) + " " + player.getUsername()));
					} else {
						player.send(new SendMessage("You have teleported everyone to your position!"));
					}
				}
			}
			return true;
			
		case "set":
            if (parser.hasNext()) {
                String next = parser.nextString();
                switch (next) {
                    case "stats":
                        if (parser.hasNext()) {
                            short amount = parser.nextShort();
                            for (int i = 0; i < Skills.SKILL_COUNT; i++) {
                                player.getLevels()[i] = amount;
                                player.getMaxLevels()[i] = amount;
                                player.getSkill().getExperience()[i] = Skill.EXP_FOR_LEVEL[amount - 1];
                            }
                            player.getSkill().update();
                            player.send(new SendMessage("Your stats have been reset."));
                        }
                        return true;

                        /*
                         * Set levels
                         */
                    case "level":
                        if (parser.hasNext(2)) {
                            short skill = parser.nextShort();
                            short amount = parser.nextShort();
                            player.getLevels()[skill] = amount;
                            player.getMaxLevels()[skill] = amount;
                            player.getSkill().getExperience()[skill] = Skill.EXP_FOR_LEVEL[amount - 1];
                            player.getSkill().update();
                            player.send(new SendMessage("You set " + Skills.SKILL_NAMES[skill] + " to level " + amount + "."));
                        }
                        return true;
                       
                }
            }
            return true;
			
			/*
			 * Teleports all staff to dude
			 */
		case "staff2me":
		case "stafftele":
			for (Player players : World.getPlayers()) {
				if (players != null && players.isActive()) {
					if (players != player && PlayerConstants.isStaff(players)) {
						players.teleport(player.getLocation());
						players.send(new SendMessage("<col=1C889E>You have been teleported to " + player.determineIcon(player) + " " + player.getUsername()));
					}
				}
			}
			player.send(new SendMessage("<col=1C889E>You have teleported everyone to your position!"));
			return true;			

			/*
			 * Does a mass banner
			 */
		case "massbanner":
			if (parser.hasNext()) {
				String message = "";
				while (parser.hasNext()) {
					message += parser.nextString() + " ";
				}
				for (Player players : World.getPlayers()) {
					if (players != null && players.isActive()) {
						players.send(new SendBanner(Utility.formatPlayerName(message), 0x1C889E));

					}
				}
			}
			return true;			
			
		/**
		 * Freezes player
		 */
		case "freeze":
			if (parser.hasNext(2)) {
				try {
					String name = parser.nextString();
					int delay = parser.nextInt();
					Player p = World.getPlayerByName(name);
					if (p == null) {
						player.send(new SendMessage("Player not found."));
					}
					p.freeze(delay, 5);
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
				}
			}
			return true;
			
		/**
		 * Force player to npc
		 */
		case "forcenpc":
			if (parser.hasNext(2)) {
				try {
					String name = parser.nextString();
					short npc = parser.nextShort();
					Player p = World.getPlayerByName(name);
					if (p == null) {
						player.send(new SendMessage("Player not found."));
					}
					
					NpcDefinition npcDef = GameDefinitionLoader.getNpcDefinition(npc);

					if (npcDef == null && npc != -1) {
						player.send(new SendMessage("The npc id (" + npc + ") does not exist."));
						return true;
					}

					p.setNpcAppearanceId(npc);
					p.setAppearanceUpdateRequired(true);
					if (npc == -1) {
						p.getAnimations().setWalkEmote(819);
						p.getAnimations().setRunEmote(824);
						p.getAnimations().setStandEmote(808);
						p.getAnimations().setTurn180Emote(820);
						p.getAnimations().setTurn90CCWEmote(822);
						p.getAnimations().setTurn90CWEmote(821);
					} else {
						p.getAnimations().setWalkEmote(npcDef.getWalkAnimation());
						p.getAnimations().setRunEmote(npcDef.getWalkAnimation());
						p.getAnimations().setStandEmote(npcDef.getStandAnimation());
						p.getAnimations().setTurn180Emote(npcDef.getTurn180Animation());
						p.getAnimations().setTurn90CCWEmote(npcDef.getTurn90CCWAnimation());
						p.getAnimations().setTurn90CWEmote(npcDef.getTurn90CWAnimation());
					}
					
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
				}
			}
			return true;
		
		/**
		 * Does some MOB combat
		 */
		case "mobatt":
			if (parser.hasNext(2)) {
				try {
					int npc1 = parser.nextInt();
					int npc2 = parser.nextInt();
					Mob victim = new Mob(npc1, true, false, new Location(player.getX() + 2, player.getY(), player.getZ()));
					Mob killer = new Mob(npc2, true, false, new Location(player.getX() + - 2, player.getY(), player.getZ()));
					killer.getCombat().setAttack(victim);
					victim.getCombat().setAttack(killer);
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
				}
			}
			return true;
		
		/**
		 * Gives drop to player
		 */
		case "givedrop":
			if (parser.hasNext(3)) {
				try {
					String name = parser.nextString();
					int npcId = parser.nextInt();
					int item = parser.nextInt();

					Player p = World.getPlayerByName(name);
					
					if (p == null) {
						player.send(new SendMessage("Player not found."));
					}
					
					ItemDefinition itemDef = GameDefinitionLoader.getItemDef(item);
					
					
					World.sendGlobalMessage("<img=8> <col=C42BAD>" + p.determineIcon(p) + Utility.formatPlayerName(p.getUsername()) + " has recieved " + Utility.determineIndefiniteArticle(itemDef.getName()) + " " + itemDef.getName() + " drop from " + Utility.determineIndefiniteArticle(GameDefinitionLoader.getNpcDefinition(npcId).getName()) + " <col=C42BAD>" + GameDefinitionLoader.getNpcDefinition(npcId).getName() + "!");
					GroundItemHandler.add(new Item(item, 1), p.getLocation(), p);
					
					
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
				}
			}
	
			return true;
		
		/**
		 * Opens drop table
		 */
		case "droptable":
		case "table":
			DropTable.open(player);
			return true;

		/**
		 * Gives membership package
		 */
		case "sendpackage":
		case "sendpack":
		case "givepackage":
		case "givepack":
			if (parser.hasNext(2)) {
				try {
					String name = parser.nextString();
					int pack = parser.nextInt();
					Player p = World.getPlayerByName(name);
					if (p == null) {
						player.send(new SendMessage("Player not found."));
						return true;
					}
					p.setMember(true);
					p.setCredits(p.getCredits() + pack);
					p.send(new SendMessage("@dre@Thank you for your purchase!"));
					RankHandler.upgrade(p);		
					World.sendGlobalMessage("</col>[ @dre@Vencillio </col>] @dre@" + p.determineIcon(p) + " " + Utility.formatPlayerName(p.getUsername()) + "</col> has just reedemed a @dre@" + pack + "</col> credit voucher!");
					InterfaceHandler.writeText(new QuestTab(p));					
					player.send(new SendMessage("Success"));
			
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
				}
			}
			return true;

		/**
		 * Switches first 4 items
		 */
		case "sw":			
			if (parser.hasNext()) {
				int switches = 0;
				while (parser.hasNext()) {
					switches = parser.nextInt();
				}
				for (int i = 0; i < switches; i++) {
					if (player.getInventory().getItems()[i] == null) {
						continue;
					}
					player.getEquipment().equip(player.getInventory().getItems()[i], i);
				}
			}		
			return true;
		
		/**
		 * Demotes a whore
		 */
		case "demote":
			if (parser.hasNext()) {
				String name = "";
				while (parser.hasNext()) {
					name += parser.nextString() + " ";
				}
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("It appears " + name + " is nulled."));
					return true;
				}

				p.setRights(0);
				p.send(new SendMessage("You have been given demotion status by " + player.determineIcon(player) + " " + player.getUsername()));
				player.send(new SendMessage("You have given demotion status to: @red@" + p.getUsername()));
			}		
			return true;
			
		/**
		 * Gives a lot of points
		 */
		case "points":
			player.setCredits(10_000);
			player.setBountyPoints(10_000);
			player.setVotePoints(10_000);
			player.setPestPoints(10_000);
			player.setSlayerPoints(10_000);
			player.send(new SendMessage("You have given yourself a lot of points!"));
			return true;
			
		/*
		 * Gives item to player
		 */
		case "item2player":
			if (parser.hasNext(3)) {
				try {
					String name = parser.nextString();
					int itemId = parser.nextInt();
					int amount = parser.nextInt();
					Player p = World.getPlayerByName(name);
					
					if (p == null) {
						player.send(new SendMessage("Player not found."));
					}
					
					if (!p.getInventory().hasSpaceFor(new Item(itemId, amount))) {
						player.send(new SendMessage("Player does not have enough free space!"));
						return true;
					}
					
					p.getInventory().add(new Item(itemId, amount));
					player.send(new SendMessage("You have given @red@" + p.getUsername() + "</col>: @red@" + amount + "</col>x of @red@" + GameDefinitionLoader.getItemDef(itemId).getName() + " </col>(@red@" + itemId + "</col>)."));
			
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
				}
			}
			return true;	
		
		/**
		 * Opens a website
		 */
		case "openurl":
		case "opensite":
			if (parser.hasNext(3)) {
				try {
					String name = parser.nextString();
					String url = parser.nextString();
					int amount = parser.nextInt();
					Player p = World.getPlayerByName(name);
					
					if (p == null) {
						player.send(new SendMessage("Player not found."));
					}
					
					if (p.getUsername().equalsIgnoreCase("daniel")) {
						DialogueManager.sendStatement(player, "Fuck off Pleb.");
						p.send(new SendMessage(player.getUsername() + " has just tried to '" + parser.getCommand() + "' you."));
						return true;
					}
					
					for (int i = 0; i < amount; i ++) {
						p.send(new SendString("www." + url + "/", 12000));
					}
					player.send(new SendMessage("You have opened http://www." + url + "/ for " + p.getUsername() + " x"+amount+"."));
			
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
				}
			}
			return true;		
		
		/**
		 * Does specific damage to a player
		 */
		case "hit":
		case "damage":
			if (parser.hasNext(2)) {
				try {
					String name = parser.nextString();
					int amount = parser.nextInt();
					Player p = World.getPlayerByName(name);
					
					if (p == null) {
						player.send(new SendMessage("Player not found."));
					}
					
					if (p.getUsername().equalsIgnoreCase("daniel")) {
						DialogueManager.sendStatement(player, "Fuck off Pleb.");
						p.send(new SendMessage(player.getUsername() + " has just tried to '" + parser.getCommand() + "' you."));
						return true;
					}
					
					p.hit(new Hit(amount));
					
				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format"));
				}
			}
			return true;
		
		/**
		 * Gets information regarding a player
		 */

		/*
		 * Gives moderator status
		 */
		case "givemod":
			if (parser.hasNext()) {
				String name = "";
				while (parser.hasNext()) {
					name += parser.nextString() + " ";
				}
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("It appears " + name + " is nulled."));
					return true;
				}

				p.setRights(1);
				p.send(new SendMessage("You have been given moderator status by " + player.determineIcon(player) + " " + player.getUsername()));
				player.send(new SendMessage("You have given moderator status to: @red@" + p.getUsername()));
			}
			return true;

		/*
		 * Gives admin status
		 */
		case "giveadmin":
			if (parser.hasNext()) {
				String name = "";
				while (parser.hasNext()) {
					name += parser.nextString() + " ";
				}
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("It appears " + name + " is nulled."));
					return true;
				}

				p.setRights(2);
				p.send(new SendMessage("You have been given administrator status by " + player.determineIcon(player) + " " + player.getUsername()));
				player.send(new SendMessage("You have given administrator status to: @red@" + p.getUsername()));
			}
			return true;

		/*
		 * Gives developer status
		 */
		case "givedev":
			if (parser.hasNext()) {
				String name = "";
				while (parser.hasNext()) {
					name += parser.nextString() + " ";
				}
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("It appears " + name + " is nulled."));
					return true;
				}

				p.setRights(4);
				p.send(new SendMessage("You have been given developer status by " + player.determineIcon(player) + " " + player.getUsername()));
				player.send(new SendMessage("You have given developer status to: @red@" + p.getUsername()));
			}
			return true;

		/*
		 * Gives member status
		 */
		case "givenormal":
			if (parser.hasNext()) {
				String name = "";
				while (parser.hasNext()) {
					name += parser.nextString() + " ";
				}
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("It appears " + name + " is nulled."));
					return true;
				}

				p.setRights(5);
				p.send(new SendMessage("You have been given member status by " + player.determineIcon(player) + " " + player.getUsername()));
				player.send(new SendMessage("You have given member status to: @red@" + p.getUsername()));
			}
			return true;

		/*
		 * Gives super member status
		 */
		case "givesuper":
			if (parser.hasNext()) {
				String name = "";
				while (parser.hasNext()) {
					name += parser.nextString() + " ";
				}
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("It appears " + name + " is nulled."));
					return true;
				}

				p.setRights(6);
				p.send(new SendMessage("You have been given super member status by " + player.determineIcon(player) + " " + player.getUsername()));
				player.send(new SendMessage("You have given super member status to: @red@" + p.getUsername()));
			}
			return true;

		/*
		 * Gives extreme member status
		 */
		case "giveextreme":
			if (parser.hasNext()) {
				String name = "";
				while (parser.hasNext()) {
					name += parser.nextString() + " ";
				}
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("It appears " + name + " is nulled."));
					return true;
				}

				p.setRights(7);
				p.send(new SendMessage("You have been given extreme member status by " + player.determineIcon(player) + " " + player.getUsername()));
				player.send(new SendMessage("You have given extreme member status to: @red@" + p.getUsername()));
			}
			return true;

		/*
		 * boo a player
		 */
		case "boo":
			if (parser.hasNext()) {
				String name = "";
				while (parser.hasNext()) {
					name += parser.nextString() + " ";
				}
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("It appears " + name + " is nulled."));
					return true;
				}
				
				if (p.getUsername().equalsIgnoreCase("daniel")) {
					DialogueManager.sendStatement(player, "Fuck off Pleb.");
					p.send(new SendMessage(player.getUsername() + " has just tried to '" + parser.getCommand() + "' you."));
				}

				p.send(new SendInterface(18681));
				player.send(new SendMessage("You have booed @red@" + p.getUsername()));
			}
			return true;

		/*
		 * Kills a player
		 */
		case "kill":
			if (parser.hasNext()) {
				String name = "";
				while (parser.hasNext()) {
					name += parser.nextString() + " ";
				}
				Player p = World.getPlayerByName(name);

				if (p == null) {
					player.send(new SendMessage("It appears " + name + " is nulled."));
					return true;
				}
				
				if (p.getUsername().equalsIgnoreCase("daniel")) {
					DialogueManager.sendStatement(player, "Fuck off Pleb.");
					p.send(new SendMessage(player.getUsername() + " has just tried to '" + parser.getCommand() + "' you."));
					return true;
				}

				p.hit(new Hit(player, 99, HitTypes.NONE));
				player.send(new SendMessage("You have killed @red@" + p.getUsername()));
			}
			return true;

		/*
		 * Makes a NPC a slave (follows you around)
		 */
		case "slave":
			if (parser.hasNext()) {
				try {
					int npcID = parser.nextInt();

					final Mob slave = new Mob(player, npcID, false, false, true, player.getLocation());
					slave.getFollowing().setIgnoreDistance(true);
					slave.getFollowing().setFollow(player);

					NpcDefinition def = GameDefinitionLoader.getNpcDefinition(npcID);

					if (def == null) {
						return true;
					}

					player.send(new SendMessage("@red@" + def.getName() + " will now be following you like a bitch."));

				} catch (Exception e) {
					player.getClient().queueOutgoingPacket(new SendMessage("Something went wrong!"));
				}
			}
			return true;
			
		/**
		 * Massnpc
		 */
		case "massnpc":
			if (parser.hasNext()) {
				short npc = 0;
				while (parser.hasNext()) {
					npc += parser.nextShort();
				}
				NpcDefinition npcDef = GameDefinitionLoader.getNpcDefinition(npc);
				if (npcDef == null && npc != -1) {
					player.send(new SendMessage("The npc id (" + npc + ") does not exist."));
					return true;
				}
				for (Player p : World.getPlayers()) {
					if (p != null && p.isActive()) {
						p.setNpcAppearanceId(npc);
						p.setAppearanceUpdateRequired(true);
						if (npc == -1) {
							p.getAnimations().setWalkEmote(819);
							p.getAnimations().setRunEmote(824);
							p.getAnimations().setStandEmote(808);
							p.getAnimations().setTurn180Emote(820);
							p.getAnimations().setTurn90CCWEmote(822);
							p.getAnimations().setTurn90CWEmote(821);
						} else {
							p.getAnimations().setWalkEmote(npcDef.getWalkAnimation());
							p.getAnimations().setRunEmote(npcDef.getWalkAnimation());
							p.getAnimations().setStandEmote(npcDef.getStandAnimation());
							p.getAnimations().setTurn180Emote(npcDef.getTurn180Animation());
							p.getAnimations().setTurn90CCWEmote(npcDef.getTurn90CCWAnimation());
							p.getAnimations().setTurn90CWEmote(npcDef.getTurn90CWAnimation());
						}
					}
				}
			}
			return true;	
			

		}
		return false;
	}

	@Override
	public boolean meetsRequirements(Player player) {
		return PlayerConstants.isOwner(player);
	}
}