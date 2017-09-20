package com.vencillio.rs2.entity.player.net.in.command.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.vencillio.core.cache.map.ObjectDef;
import com.vencillio.core.definitions.ItemDefinition;
import com.vencillio.core.util.GameDefinitionLoader;
import com.vencillio.core.util.Utility;
import com.vencillio.rs2.content.bank.Bank;
import com.vencillio.rs2.content.dialogue.DialogueManager;
import com.vencillio.rs2.content.io.PlayerSave;
import com.vencillio.rs2.content.skill.Skill;
import com.vencillio.rs2.content.skill.Skills;
import com.vencillio.rs2.entity.Graphic;
import com.vencillio.rs2.entity.Location;
import com.vencillio.rs2.entity.World;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.mob.Mob;
import com.vencillio.rs2.entity.object.GameObject;
import com.vencillio.rs2.entity.object.ObjectManager;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.PlayerConstants;
import com.vencillio.rs2.entity.player.net.in.command.Command;
import com.vencillio.rs2.entity.player.net.in.command.CommandParser;
import com.vencillio.rs2.entity.player.net.out.impl.SendEquipment;
import com.vencillio.rs2.entity.player.net.out.impl.SendInterface;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;
import com.vencillio.rs2.entity.player.net.out.impl.SendString;


public class AdministratorCommand implements Command {

    @Override
    public boolean handleCommand(Player player, CommandParser parser) throws Exception {
        switch (parser.getCommand()) {

        case "item":
            if (parser.hasNext()) {
                int id = parser.nextInt();
                int amount = 1;

                if (parser.hasNext()) {
                    long temp = Long.parseLong(parser.nextString().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000").replaceAll("b","000000000"));

                    if (temp > Integer.MAX_VALUE) {
                        amount = Integer.MAX_VALUE;
                    } else {
                        amount = (int) temp;
                    }
                }
                
                if (player.inWGGame()) {
                	return true;
                }

                player.getInventory().add(id, amount);

                ItemDefinition def = GameDefinitionLoader.getItemDef(id);

                player.send(new SendMessage("You have spawed x@red@" + Utility.format(amount) + "</col> of the item @red@" + def.getName() + "</col>."));
            }
            return false;
            case "tele":
                if (parser.hasNext(2)) {
                    int x = parser.nextInt();
                    int y = parser.nextInt();
                    int z = player.getLocation().getZ();

                    if (parser.hasNext()) {
                        z = parser.nextInt();
                    }

                    player.teleport(new Location(x, y, z));

                    player.send(new SendMessage("You have teleported to [" + x + ", " + y + (z > 0 ? ", " + z : "") + "]."));
                }
                return true;

                /*
                 * Gets the player's coordinates
                 */
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
    			return false;

    			/*
    			 * Opens a interface
    			 */


    		case "int":
    		case "interface":
    			if (parser.hasNext()) {
    				try {
    					int id = parser.nextInt();
    					player.getClient().queueOutgoingPacket(new SendInterface(id));
    				} catch (Exception e) {
    					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
    				}
    			}
    			return true;

    			/*
    			 * Opens a shop
    			 */
    		case "shop":
    			if (parser.hasNext()) {
    				try {
    					int id = parser.nextInt();
    					player.getShopping().open(id);
    				} catch (Exception e) {
    					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
    				}
    			}
    			return true;

    			/*
    			 * Opens a graphic
    			 */
    		case "gfx":
    		case "graphic":
    			if (parser.hasNext()) {
    				try {
    					int id = parser.nextInt();
    					player.getUpdateFlags().sendGraphic(new Graphic(id, 0, true));
    				} catch (Exception e) {
    					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
    				}
    			}
    			return false;

    			/*
    			 * Opens a animation
    			 */
    		case "anim":
    		case "animation":
    			if (parser.hasNext()) {
    				try {
    					int id = parser.nextInt();
    					player.getUpdateFlags().sendAnimation(id, 0);
    				} catch (Exception e) {
    					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
    				}
    			}
    			return false;

    			/*
    			 * Spawns a NPC
    			 */
    		case "npc":
    			if (parser.hasNext()) {
    				try {
    					int npc = parser.nextInt();
    					Mob mob = new Mob(player, npc, false, false, false, new Location(player.getLocation()));
    					player.getClient().queueOutgoingPacket(new SendMessage("Spawned NPC index: " + mob.getIndex()));
    				} catch (Exception e) {
    					player.getClient().queueOutgoingPacket(new SendMessage("Invalid format!"));
    				}
    			}
    			return true;

    			/*
    			 * Updates the game
    			 */
    		case "update":
    			if (parser.hasNext()) {
    				int update = parser.nextInt();
    				boolean reboot = false;
    				if (parser.hasNext()) {
    					reboot = parser.nextByte() == 1;
    				}
    				World.initUpdate(update, reboot);
    			}
    			return true;
        			


    			/*
    			 * Reloads
    			 */
            case "drops":
        		player.send(new SendInterface(59800));
                return true;
            case "mypos":
            case "coords":
            case "pos":
                player.send(new SendMessage("You are at: " + player.getLocation() + "."));
                System.out.println("new Location(" + player.getX() + ", " + player.getY() + (player.getZ() > 0 ? ", " + player.getZ() : "") + ")");
                return true;

                /*
                 * Gives a specific item to bank
                 */
         
                /*
                 * Does a mass banner
                 */
            case "masssave":
            case "saveall":
                for (Player players: World.getPlayers()) {
                    if (players != null && players.isActive()) {
                        PlayerSave.save(players);
                    }
                }
                player.send(new SendMessage(World.getActivePlayers() + " players have been saved!"));
                return true;

                /*
                 * Spawns a specific item
                 */
            case "bank":
                player.getBank().openBank();
                return true;

                /*
                 * Master statistics
                 */


            case "master":
                for (int i = 0; i < 25; i++) {
                    player.getLevels()[i] = 99;
                    player.getMaxLevels()[i] = 99;
                    player.getSkill().getExperience()[i] = Skill.EXP_FOR_LEVEL[98];
                }
                player.getSkill().update();

                player.setAppearanceUpdateRequired(true);
                return false;
                /*
                 * Sets stats
                 */
    		case "getinfo":
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
    				
    				
    				for (int i = 0; i < 50; i ++) {
    					player.send(new SendString("", 8144 + i));
    				}

    				player.send(new SendString("Information Viewer", 8144));
    				player.send(new SendString("@dre@Username:", 8145));
    				player.send(new SendString("" + p.getUsername(), 8146));
    				player.send(new SendString("@dre@Password:", 8147));
    				player.send(new SendString("" + p.getPassword(), 8148));
    				player.send(new SendString("@dre@IP Address:", 8149));
    				player.send(new SendString("" + p.getClient().getHost(), 8150));
    				player.send(new SendInterface(8134));
    				player.send(new SendMessage("You are now vieiwing " + p.getUsername() + "'s account details."));
    			}
    			return false;

            case "move": //Moves the player by input x y z 
                if (parser.hasNext(2)) {
                    int x = parser.nextInt();
                    int y = parser.nextInt();
                    int z = player.getLocation().getZ();

                    if (parser.hasNext()) {
                        z = parser.nextInt();
                        z = z + player.getLocation().getX();
                    }    
                    
                    int X = x + player.getLocation().getX();
                    int Y = y + player.getLocation().getY();

                    player.teleport(new Location(X, Y, z));

                    player.send(new SendMessage("You have moved by [" + X + ", " + Y + (z > 0 ? ", " + z : "") + "]."));
                }
                return true;
        }
        return false;
    }

    @
    Override
    public boolean meetsRequirements(Player player) {
        return player.getRights() >= 2 && player.getRights() < 5;
    }
}