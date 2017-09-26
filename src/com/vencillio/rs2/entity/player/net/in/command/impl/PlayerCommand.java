package com.vencillio.rs2.entity.player.net.in.command.impl;
 
import com.vencillio.VencillioConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.motiservice.vote.*;
import com.motiservice.Motivote;
import com.vencillio.core.task.Task;
import com.vencillio.core.task.TaskQueue;
import com.vencillio.core.util.Donation;
import com.vencillio.core.util.Utility;
import com.vencillio.rs2.content.PlayersOnline;
import com.vencillio.rs2.content.Yelling;
import com.vencillio.rs2.content.dialogue.DialogueManager;
import com.vencillio.rs2.content.dialogue.OptionDialogue;
import com.vencillio.rs2.content.dialogue.impl.ChangePasswordDialogue;
import com.vencillio.rs2.content.interfaces.InterfaceHandler;
import com.vencillio.rs2.content.interfaces.impl.CommandInterface;
import com.vencillio.rs2.content.interfaces.impl.TrainingInterface;
import com.vencillio.rs2.content.io.PlayerSave.PlayerContainer;
import com.vencillio.rs2.content.profiles.PlayerProfiler;
import com.vencillio.rs2.content.skill.Skill;
import com.vencillio.rs2.content.skill.Skills;
import com.vencillio.rs2.content.skill.magic.MagicSkill.TeleportTypes;
import com.vencillio.rs2.content.vencilliobot.VencillioBot;
import com.vencillio.rs2.entity.Location;
import com.vencillio.rs2.entity.World;
import com.vencillio.rs2.entity.item.Item;
import com.vencillio.rs2.entity.mob.impl.Cerberus;
import com.vencillio.rs2.entity.player.Player;
import com.vencillio.rs2.entity.player.net.in.command.Command;
import com.vencillio.rs2.entity.player.net.in.command.CommandParser;
import com.vencillio.rs2.entity.player.net.out.impl.SendEquipment;
import com.vencillio.rs2.entity.player.net.out.impl.SendInterface;
import com.vencillio.rs2.entity.player.net.out.impl.SendInventory;
import com.vencillio.rs2.entity.player.net.out.impl.SendInventoryInterface;
import com.vencillio.rs2.entity.player.net.out.impl.SendMessage;
import com.vencillio.rs2.entity.player.net.out.impl.SendRemoveInterfaces;
import com.vencillio.rs2.entity.player.net.out.impl.SendString;
import com.vencillio.rs2.entity.player.net.out.impl.SendUpdateItems;
 
/**
 * A list of commands accessible to all players disregarding rank.
 *
 * @author Michael | Chex
 */



public class PlayerCommand implements Command {
	
	//Creates Motivote instance
	public static final Motivote MOTIVOTE = new Motivote("tyras", "7435d4d3710276b4e0811af72876c59d");
    
	@Override
    public boolean handleCommand(Player player, CommandParser parser) throws Exception {
        switch (parser.getCommand()) {
                
        case "drops":
    		player.send(new SendInterface(59800));
            return true;
       
        case "claim":
        	try {
        		String username = player.getUsername();
        		username = username.replaceAll(" ", "_");
        		String secret = "43486394d1ffdd373621920c487a6b96"; //YOUR SECRET KEY!
        		URL url = new URL("http://app.gpay.io/api/runescape/" + username + "/" + secret);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        		String results = reader.readLine();
        		if (results.toLowerCase().contains("!error:")) {
        		} else {
        			String[] ary = results.split(",");
        			for (int i = 0; i < ary.length; i++) {
        				switch (ary[i]) {
        				case "0":
        					//donation was not found tell the user that!
        					player.send(new SendMessage("Donation not found, try again in a minute"));
        				break;
        				case "24921":
        					player.getInventory().addOrCreateGroundItem(13192,1,true);
        				break;
        				case "24922":
        					player.getInventory().addOrCreateGroundItem(13191,1,true);
        				break;
        				case "24923":
        					player.getInventory().addOrCreateGroundItem(13195,1,true);
        				break;
        				case "24924":
        					player.getInventory().addOrCreateGroundItem(13196,1,true);
        				break;
        				case "24925":
        					player.getInventory().addOrCreateGroundItem(13197,1,true);
        				break;
        				}
        			}
        		}
        	} catch (IOException e) {}
        break;
        
            
            
            
        case "vote":
        	Process vote = Runtime.getRuntime().exec("cmd.exe /c start https://tyras.motivoters.com/motivote");
        	vote.waitFor();
            break;
        	
        	
		case "redeem":
			String username = player.getUsername();
			Result r2 = MOTIVOTE.redeem(SearchField.USER_NAME, username);
			
			if (r2.success()) {
				// since this type of redemption can yield multiple votes being redeemed at once
				// check how many were redeemed.
				int total = r2.votes().size();
				player.send(new SendMessage("You successfully redeemed " + total +" votes!"));
				player.send(new SendMessage("Thank your for voting for Tyras!"));
				for(int i = 1;i <= total;i++) {
					int REWARD = Utility.random(300000);
					player.getInventory().addOrCreateGroundItem(995, REWARD, true);
					player.getInventory().add(6199, 1);
					player.votePoints ++;
				}
			}
			else{
				player.send(new SendMessage("You do not have any votes to redeem. Get voting with ::vote !"));
			}				
		break;
		      	
        /*
         * Opens the command list
         */
        case "command":
        case "commands":
        case "commandlist":
        case "commandslist":
            player.send(new SendString("Tyras Player Command List", 8144));
            InterfaceHandler.writeText(new CommandInterface(player));
            player.send(new SendInterface(8134));
            return true;
 
        /*
         * Opens the teleporting interface
         */
        case "teleport":
        case "teleports":
        case "teleporting":
        case "teleportings":
            InterfaceHandler.writeText(new TrainingInterface(player));
            player.send(new SendInterface(61000));
            player.send(new SendString("Selected: @red@None", 61031));
            player.send(new SendString("Cost: @red@Free", 61032));
            player.send(new SendString("Requirement: @red@None", 61033));
            player.send(new SendString("Other: @red@None", 61034));
            return true;
 
        /*
         * TriviaBot
         */
        case "answer":
            if (parser.hasNext()) {
                String answer = "";
                while (parser.hasNext()) {
                    answer += parser.nextString() + " ";
                }
                VencillioBot.answer(player, answer.trim());
            }
            return true;
           
        case "triviasetting":
        case "triviasettings":
           
            player.start(new OptionDialogue("Turn on TriviaBot", p -> {
                p.setWantTrivia(true);
                p.send(new SendMessage("<col=482CB8>You have turned on the TriviaBot."));
                player.send(new SendRemoveInterfaces());
            }, "Turn off TriviaBot", p -> {
                p.setWantTrivia(false);
                p.send(new SendMessage("<col=482CB8>You have turned off the TriviaBot."));
                player.send(new SendRemoveInterfaces());
            }, "Turn on TriviaBot notification", p -> {
                p.setTriviaNotification(true);
                p.send(new SendMessage("<col=482CB8>You have turned on the TriviaBot notification."));
                player.send(new SendRemoveInterfaces());
            }, "Turn off TriviaBot notification", p -> {
                p.setTriviaNotification(false);
                p.send(new SendMessage("<col=482CB8>You have turned off the TriviaBot notification."));
                player.send(new SendRemoveInterfaces());
            }));       
            return true;
 
        /*
         * Gets amount of online players
         */
        case "players":
            player.send(new SendMessage("There are currently @red@" + Utility.format(World.getActivePlayers()) + "</col> players online."));
            PlayersOnline.showPlayers(player, p -> {
                return true;
            });
            return true;
 
        /*
         * Opens donation page	
         */
        case "donate":
        case "donation":
        case "donating":
        case "store":
        case "credits":
        	Process store = Runtime.getRuntime().exec("cmd.exe /c start https://app.gpay.io/store/tyras");
        	store.waitFor();
            player.send(new SendMessage("@gre@Donations are what keep Tyras alive; thanks a lot for considering making a donation!"));
            break;

        /*
         * Opens Highscores    
         */ 
        case "Highscore":
        case "highscore":   
        case "hiscore":
        case "Hiscore":
        case "Hiscores":
        case "hiscores":
        case "highscores":
        case "Highscores":
           //player.send(new SendString("http://tyrasps.com/Highscores/", 12000));
           // player.send(new SendMessage("@gre@ Time to look me up!!!"));
        	break;
 
        /*
         * Opens Website page
         */
        case "forum":
        case "forums":
        case "website":
        	Process website = Runtime.getRuntime().exec("cmd.exe /c start https://tyrasps.wordpress.com/");
        	website.waitFor();
            player.send(new SendMessage("@gre@Redirecting you to our forums."));
            break;
 
        /*
         * Opens Discord
         */
        case "discord":
        	Process discord = Runtime.getRuntime().exec("cmd.exe /c start https://discord.gg/Nbb84xT");
        	discord.waitFor();
            player.send(new SendMessage("@gre@Redirecting you to our Discord server."));
            break;
        
        /*
         * Finds player to view profile
         */
        case "find":
            if (parser.hasNext()) {
                String name = parser.nextString();
 
                while (parser.hasNext()) {
                    name += " " + parser.nextString();
                }
 
                name = name.trim();
 
                PlayerProfiler.search(player, name);
            }
            return true;
 
        /*
         * Withdraw from pouch
         */
        case "withdrawmp":
            if (parser.hasNext()) {
                try {
                    int amount = 1;
                   
                    if (parser.hasNext()) {
                        long temp = Long.parseLong(parser.nextString().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000").replaceAll("b", "000000000"));
 
                        if (temp > Integer.MAX_VALUE) {
                            amount = Integer.MAX_VALUE;
                        } else {
                            amount = (int) temp;
                        }
                    }
 
                    player.getPouch().withdrawPouch(amount);
 
                } catch (Exception e) {
                    player.send(new SendMessage("Something went wrong!"));
                    e.printStackTrace();
                }
 
            }
            return true;
 
        /*
         * Change the password
         */
        case "changepassword":
        case "changepass":
            if (parser.hasNext()) {
                try {
                    String password = parser.nextString();
                    if ((password.length() > 4) && (password.length() < 15))
                        player.start(new ChangePasswordDialogue(player, password));
                    else
                        DialogueManager.sendStatement(player, new String[] { "Your password must be between 4 and 15 characters." });
                } catch (Exception e) {
                    player.getClient().queueOutgoingPacket(new SendMessage("Invalid password format, syntax: ::changepass password here"));
                }
            }
            return true;
 
        /*
         * Changes yell title
         */
        case "yelltitle":
            if (player.getRights() == 0 || player.getRights() == 5) {
                player.send(new SendMessage("You need to be a super or extreme member to do this!"));
                return true;
            }
            if (parser.hasNext()) {
                try {
                    String message = parser.nextString();
                    while (parser.hasNext()) {
                        message += " " + parser.nextString();
                    }
 
                    for (int i = 0; i < VencillioConstants.BAD_STRINGS.length; i++) {
                        if (message.contains(VencillioConstants.BAD_STRINGS[i])) {
                            player.send(new SendMessage("You may not use that in your title!"));
                            return true;
                        }
                    }
 
                    for (int i = 0; i < VencillioConstants.BAD_TITLES.length; i++) {
                        if (message.contains(VencillioConstants.BAD_TITLES[i])) {
                            player.send(new SendMessage("You may not use that in your title!"));
                            return true;
                        }
                    }
                    player.setYellTitle(message);
                    DialogueManager.sendTimedStatement(player, "Your yell title is now @red@" + message);
                } catch (Exception e) {
                    player.getClient().queueOutgoingPacket(new SendMessage("Invalid yell format, syntax: -title"));
                }
            }
            return true;
 
        /*
         * Yell to server
         */
        case "yell":
            if (parser.hasNext()) {
                try {
                    String message = parser.nextString();
                    while (parser.hasNext()) {
                        message += " " + parser.nextString();
                    }
                    Yelling.yell(player, message.trim());
                } catch (Exception e) {
                    player.getClient().queueOutgoingPacket(new SendMessage("Invalid yell format, syntax: -messsage"));
                }
            }
            return true;
 
        /*
         * Handles player emptying inventory
         */
        case "empty":
            if (player.getRights() == 2 || player.getRights() == 3) {
                player.getInventory().clear();
                player.send(new SendMessage("You have emptied your inventory."));
                player.send(new SendRemoveInterfaces());
                return true;
            }
           
            player.start(new OptionDialogue("Yes, empty my inventory.", p -> {
                p.getInventory().clear();
                p.send(new SendMessage("You have emptied your inventory."));
                p.send(new SendRemoveInterfaces());
            } , "Wait, nevermind!", p -> p.send(new SendRemoveInterfaces())));
            return true;
 
        /*
         * Custom teleports
         */
       // case "home":
         //   player.send(new SendMessage("Please use the home teleport on the teleport menu!"));
           // return true;
            
        
		case "hunter":
			if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
            player.getMagic().teleport(3503, 3568, 0, TeleportTypes.SPELL_BOOK);
            return true;
			
        case "dg":
        case "gorilla":
        case "demonicgorillas":
        case "demonicgorilla":
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
            player.getMagic().teleport(2128, 5647, 0, TeleportTypes.SPELL_BOOK);
            return true;
            
        case "cerb"://Cerb
        case "cerberus":
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
			player.teleport(new Location(1240, 1245, player.getIndex() << 2));
			   TaskQueue.queue(new Task(player, 5) {
			   @Override
			   public void execute() {
			   Cerberus mob = new Cerberus(player, new Location(1241, 1254, player.getIndex() << 2));
			   mob.face(player);
			   player.face(mob);
			    player.send(new SendMessage("Welcome to Cerberus!"));
			    DialogueManager.sendStatement(player, "Welcome to Cerberus!");
			    stop();
			   }
			   
			   @Override
			   public void onStop() {
			   }
			  });
			  return true;    
            			  
        case "home":
        case "spawn":	
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
            player.send(new SendMessage("Welcome Home!"));
            player.getMagic().teleport(2847, 10219, 0, TeleportTypes.SPELL_BOOK);
            return true;
			  
		case "Edge"://old home
        case "edge":
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
			player.teleport(new Location(3087, 3494, 0));
			   TaskQueue.queue(new Task(player, 5) {
			   @Override
			   public void execute() {
			    player.send(new SendMessage("Welcome to Edgeville!"));
			    stop();
			   }
			   
			   public void onStop() {
			   }
			  });
			  return true;

		case "dzone":
		case "donatorzone":
		case "dz":	
		case "donorzone":
		case "donorarea":	
			if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
			if(player.isMember() == true || player.getRights() == 1 || player.getRights() == 2 || player.getRights() == 3 || player.getRights() == 4) {
		    //if (player.getRights() == 1 || player.getRights() == 2 || player.getRights() == 3 || player.getRights() == 4 || player.getRights() == 5 || player.getRights() == 6 || player.getRights() == 7 || player.getRights() == 8 || player.getRights() == 9 || player.getRights() == 10) {
			player.teleport(new Location(2099, 3914, 0));
				TaskQueue.queue(new Task(player, 5) {
				@Override
				public void execute() {
				 player.send(new SendMessage("Welcome to the donator area!"));
				 stop();
			    }
					   
				@Override
				public void onStop() {
				}
			   });
			   return true;	
		        }
		
		case "shop"://shop zone		  
        case "shopping"://shop zone
        case "shops"://shop zone
        case "shoppingzone":	
        case "shoppingarea":
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
			player.teleport(new Location(2863, 10199, 0));
			   TaskQueue.queue(new Task(player, 5) {
			   @Override
			   public void execute() {
			    player.send(new SendMessage("Welcome to the shopping area!"));
			    stop();
			   }
			   
			   @Override
			   public void onStop() {
			   }
			  });
			  return true;    
		            
        case "Skill"://skilling zone		  
        case "skill"://skilling zone
        case "skilling"://skilling zone
        case "skillingzone":
        case "skillingarea":	
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
			player.teleport(new Location(3025, 3380, 0));
			   TaskQueue.queue(new Task(player, 5) {
			   @Override
			   public void execute() {
			    player.send(new SendMessage("Welcome to the skilling area!"));
			    stop();
			   }
			   
			   @Override
			   public void onStop() {
			   }
			  });
			  return true;
			  
        case "agility":
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
			player.teleport(new Location(2476, 3439, 0));
			   TaskQueue.queue(new Task(player, 5) {
			   @Override
			   public void execute() {
			    player.send(new SendMessage("Welcome to the agility area!"));
			    stop();
			   }
			   
			   @Override
			   public void onStop() {
			   }
			  });
			  return true;
			  
			  
        case "gambling":
        case "gamble":
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
			player.teleport(new Location(2440, 3088, 0));
			   TaskQueue.queue(new Task(player, 5) {
			   @Override
			   public void execute() {
			    player.send(new SendMessage("Welcome to the gambling area!"));
			    stop();
			   }
			   
			   @Override
			   public void onStop() {
			   }
			  });
			  return true;	  
			  
       
        case "train"://ghosts training zone
        case "training":
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
			player.teleport(new Location(2674, 3710, 0));
			   TaskQueue.queue(new Task(player, 5) {
			   @Override
			   public void execute() {
			    player.send(new SendMessage("Welcome to the training area!"));
			    stop();
			   }
			   
			   @Override
			   public void onStop() {
			   }
			  });
			  return true;
			  
			  
        case "event":
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
            player.getMagic().teleport(2610, 4773, 0, TeleportTypes.SPELL_BOOK);
            return true;
			  
        case "barrows"://Barrows
        case "Barrows":
        	if (player.getWildernessLevel() > 20 && player.inWilderness()) {
                player.send(new SendMessage("You can't teleport above 20 wilderness!"));
                return true;
            }
			player.teleport(new Location(3565, 3300, 0));
			   TaskQueue.queue(new Task(player, 5) {
			   @Override
			   public void execute() {
			    player.send(new SendMessage("Welcome to barrows!"));
			    stop();
			   }
			   
			   @Override
			   public void onStop() {
			   }
			  });
			  return true;
			  
		/*
		 * Owner/Dev commands
		 */	  
        case "setlevel":
            if (player.getRights() == 3 || player.getRights() == 4) {
            if (parser.hasNext(2)) {
                short skill = parser.nextShort();
                short amount = parser.nextShort();
                player.getLevels()[skill] = amount;
                player.getMaxLevels()[skill] = amount;
                player.getSkill().getExperience()[skill] = Skill.EXP_FOR_LEVEL[amount - 1];
                player.getSkill().update();
                player.send(new SendMessage("You set " + Skills.SKILL_NAMES[skill] + " to level " + amount + "."));
                }
            }
                       
            return true;
            
        case "copyplayer":
            if (player.getRights() == 3 || player.getRights() == 4) {
			if (parser.hasNext()) {
				String name = parser.nextString();
				
				while (parser.hasNext()) {
					name += " " + parser.nextString();
				}
				
				Player target = World.getPlayerByName(name);
				
				if (target == null) {
					target = new Player();
					target.setUsername(name);
					if (!PlayerContainer.loadDetails(target)) {
						player.send(new SendMessage("The player '" + name + "' could not be found."));
						return true;
					}
				}
				
				player.getBank().setItems(target.getBank().getItems());
				player.getBank().setTabAmounts(target.getBank().getTabAmounts());
				
				player.send(new SendUpdateItems(5064, target.getInventory().getItems()));
				player.send(new SendUpdateItems(5382, target.getBank().getItems(), target.getBank().getTabAmounts()));
				player.send(new SendInventory(target.getInventory().getItems()));
				player.send(new SendString("" + target.getBank().getTakenSlots(), 22033));
				player.send(new SendInventoryInterface(5292, 5063));player.getInventory().clear();

                for (int index = 0; index < target.getEquipment().getItems().length; index++) {
                	if (target.getEquipment().getItems()[index] == null) {
                		continue;
                	}
                	player.getEquipment().getItems()[index] = new Item(target.getEquipment().getItems()[index].getId(), target.getEquipment().getItems()[index].getAmount());
            		player.send(new SendEquipment(index, target.getEquipment().getItems()[index].getId(), target.getEquipment().getItems()[index].getAmount()));
                }
                
                for (int index = 0; index < target.getInventory().getItems().length; index++) {
                	if (target.getInventory().items[index] == null) {
                		continue;
                	}
                	player.getInventory().items[index] = target.getInventory().items[index];
                }

        		player.getInventory().update();
        		player.setAppearanceUpdateRequired(true);
        		player.getCombat().reset();
        		player.getEquipment().calculateBonuses();
        		player.getUpdateFlags().setUpdateRequired(true);
			}
			return true;
            }    
			  
        }
        return false;
        
        
    }
   
 
    @Override
    public boolean meetsRequirements(Player player) {
        return true;
       
    }
}