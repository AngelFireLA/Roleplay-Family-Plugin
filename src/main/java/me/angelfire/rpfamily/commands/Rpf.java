package me.angelfire.rpfamily.commands;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.CharMatcher;

import me.angelfire.rpfamily.Profile;
import me.angelfire.rpfamily.RpFamily;
import me.angelfire.rpfamily.json.ProfileSerializationManager;
import me.angelfire.rpfamily.utils.FileUtils;

public class Rpf implements @Nullable CommandExecutor, TabCompleter{
	
	
	private File savedir;
	private RpFamily plugin;
	
	
	public Rpf (RpFamily plugin) {
		this.plugin = plugin;
		this.savedir = new File(plugin.getDataFolder(), "/profiles");
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		
		Player player = (Player) sender;
		final File file = new File(savedir, player.getName() + ".json");
		final ProfileSerializationManager profileSerializationManager = plugin.getProfileSerializationManager();
    	if (args.length == 0) return false;
    	if (args[0].equalsIgnoreCase("mother")) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	
		            	final File file2 = new File(savedir, args[2] + ".json");

		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			if (profile.getGirlOrBoy() != "Unkown") {
		        				player.sendMessage(ChatColor.RED + "Erreur : veuillez dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), args[2].toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
			            	if (file2.exists()) {

			        			if (profile.getGirlOrBoy() == "Unkown") {
			        				player.sendMessage(ChatColor.RED + "Erreur : veuillez dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
			        			}
			        			else if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), player.getName(), profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), player.getName(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");
			        			if (profile.getGirlOrBoy() == "boy") {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", player.getName(), "None", "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        			else {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", player.getName(), "None", "None", "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        		}
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            	

		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	final File file2 = new File(savedir, args[2] + ".json");
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);

		        			if (profile.getGirlOrBoy() != "Unkown") {
		        				player.sendMessage(ChatColor.RED + "Erreur : veuillez dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), "None", profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		        			if (file2.exists()) {

			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), "None", profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), "None", profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");	
			        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", "None");
			        			final String json2 = profileSerializationManager.serialize(profile2);

			        			FileUtils.save(file1, json2);
			        		}
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        
		        if (args[0].equalsIgnoreCase("father") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	
		            	final File file2 = new File(savedir, args[2] + ".json");

		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			if (profile.getGirlOrBoy() != "Unkown") {
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), args[2].toString(), profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
			            	if (file2.exists()) {
			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), player.getName(), profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), player.getName(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");
			        			if (profile.getGirlOrBoy() == "boy") {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", player.getName(), "None", "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        			else {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", player.getName(), "None", "None", "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        		}
		        			}
		        			else {
		        				player.sendMessage(ChatColor.RED + "Erreur : veuillez dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            	

		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	final File file2 = new File(savedir, args[2] + ".json");
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);

		        			if (profile.getGirlOrBoy() != "Unkown") {
		        				
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), "None", profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		        			if (file2.exists()) {

			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), "None", profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), "None", profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");	
			        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", "None");
			        			final String json2 = profileSerializationManager.serialize(profile2);

			        			FileUtils.save(file1, json2);
			        		}
		        			}
		        			else {
		        				
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        if (args[0].equalsIgnoreCase("sister") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	
		            	final File file2 = new File(savedir, args[2] + ".json");

		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			if (profile.getGirlOrBoy() != "Unkown") {
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), args[2].toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
			            	if (file2.exists()) {
			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), args[2].toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), args[2].toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");
			        			if (profile.getGirlOrBoy() == "boy") {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", player.getName(), "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        			else {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", player.getName());
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        		}
		        			}
		        			else {
		        				player.sendMessage(ChatColor.RED + "Erreur : veuillez dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            	

		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	final File file2 = new File(savedir, args[2] + ".json");
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);

		        			if (profile.getGirlOrBoy() != "Unkown") {
		        				
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), "None", profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		        			if (file2.exists()) {

			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), "None", profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), "None");
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");	
			        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", "None");
			        			final String json2 = profileSerializationManager.serialize(profile2);

			        			FileUtils.save(file1, json2);
			        		}
		        			}
		        			else {
		        				
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        if (args[0].equalsIgnoreCase("brother") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	
		            	final File file2 = new File(savedir, args[2] + ".json");

		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			if (profile.getGirlOrBoy() != "Unkown") {
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), args[2].toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
			            	if (file2.exists()) {
			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), args[2].toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), args[2].toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");
			        			if (profile.getGirlOrBoy() == "boy") {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", player.getName(), "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        			else {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", player.getName());
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        		}
		        			}
		        			else {
		        				player.sendMessage(ChatColor.RED + "Erreur : veuillez dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            	

		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	final File file2 = new File(savedir, args[2] + ".json");
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);

		        			if (profile.getGirlOrBoy() != "Unkown") {
		        				
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), "None", profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		        			if (file2.exists()) {

			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), "None", profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), "None");
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");	
			        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", "None");
			        			final String json2 = profileSerializationManager.serialize(profile2);

			        			FileUtils.save(file1, json2);
			        		}
		        			}
		        			else {
		        				
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        if (args[0].equalsIgnoreCase("son") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	
		            	final File file2 = new File(savedir, args[2] + ".json");

		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			if (profile.getGirlOrBoy() != "Unkown") {
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), profile.getFather().toString(), args[2].toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
			            	if (file2.exists()) {
			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), player.getName(), profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), player.getName(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");
			        			if (profile.getGirlOrBoy() == "boy") {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", player.getName(), "None", "None", "None", "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        			else {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", player.getName(), "None", "None", "None", "None", "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        		}
		        			}
		        			else {
		        				player.sendMessage(ChatColor.RED + "Erreur : veuillez dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            	

		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	final File file2 = new File(savedir, args[2] + ".json");
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);

		        			if (profile.getGirlOrBoy() != "Unkown") {
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), profile.getFather().toString(), "None", profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		        			if (file2.exists()) {

			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), "None", profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), "None", profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");	
			        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", "None");
			        			final String json2 = profileSerializationManager.serialize(profile2);

			        			FileUtils.save(file1, json2);
			        		}
		        			}
		        			else {
		        				
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        if (args[0].equalsIgnoreCase("daugter") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	
		            	final File file2 = new File(savedir, args[2] + ".json");

		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			if (profile.getGirlOrBoy() != "Unkown") {
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), args[2].toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
			            	if (file2.exists()) {
			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), player.getName(), profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), player.getName(), profile2.getFather().toString(), profile2.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");
			        			if (profile.getGirlOrBoy() == "boy") {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        			else {
				        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", "None");
				        			final String json2 = profileSerializationManager.serialize(profile2);
				        			FileUtils.save(file1, json2);
								}
			        		}
		        			}
		        			else {
		        				player.sendMessage(ChatColor.RED + "Erreur : veuillez dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", args[2].toString(), "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            	

		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	final File file2 = new File(savedir, args[2] + ".json");
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);

		        			if (profile.getGirlOrBoy() != "Unkown") {
		        				
		        			
		        			final Profile profile1 = Profile.createProfile(player.getName(), profile.getGirlOrBoy(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), "None", profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		        			if (file2.exists()) {

			        			if (profile.getGirlOrBoy() == "boy") {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), profile2.getMother().toString(), "None", profile.getSon().toString(), profile2.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			        			else {
			        				final String json2 = FileUtils.loadContent(file2);
				        			final Profile profile2 = profileSerializationManager.deserialize(json2);
				        			final Profile profile3 = Profile.createProfile(args[2], profile2.getGirlOrBoy(), "None", profile2.getFather().toString(), profile2.getSon().toString(), profile.getDaughter().toString(), profile2.getBrother().toString(), profile2.getSister().toString());
				        			final String json3 = profileSerializationManager.serialize(profile3);
				        			FileUtils.save(file2, json3);
								}
			            		
			            	}
			            	else {
			        			player.sendMessage(ChatColor.RED + "Profil du joueur ciblé non existant ! Création en cours...");
			        			final File file1 = new File(savedir, args[2] + ".json");	
			        			final Profile profile2 = Profile.createProfile(args[2], "Unkown", "None", "None", "None", "None", "None", "None");
			        			final String json2 = profileSerializationManager.serialize(profile2);

			        			FileUtils.save(file1, json2);
			        		}
		        			}
		        			else {
		        				
		        			}
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé, n'oubliez pas dire si vous êtes un garçon ou une fille avec /rpfamily choose boy|girl");
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        
		        }
		        if (args[0].equalsIgnoreCase("choose")) {
		        	if(args[1].equalsIgnoreCase("boy")) {
		        		if(file.exists()) {
		        		final String json = FileUtils.loadContent(file);
	        			final Profile profile = profileSerializationManager.deserialize(json);
	        			final Profile profile1 = Profile.createProfile(player.getName(), "boy", profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getSister().toString(), profile.getBrother().toString(), profile.getSister().toString());
	        			final String json1 = profileSerializationManager.serialize(profile1);
	        			FileUtils.save(file, json1);
		        		}
		        		else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "boy", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);
		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé");
						}
		        	}
		        	if(args[1].equalsIgnoreCase("girl")) {
		        		if(file.exists()) {
		        		final String json = FileUtils.loadContent(file);
	        			final Profile profile = profileSerializationManager.deserialize(json);
	        			final Profile profile1 = Profile.createProfile(player.getName(), "girl", profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getSister().toString(), profile.getBrother().toString(), profile.getSister().toString());
	        			final String json1 = profileSerializationManager.serialize(profile1);
	        			FileUtils.save(file, json1);
		        		}
		        		else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "girl", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);
		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé");
						}
		        	}
		        }
		        if (args[0].equalsIgnoreCase("see")) {
		        	String charsToRemove = "[]";
		    		
		        	if (args.length == 1) {
		        		
		        		if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			String filtered1 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getFather().toString());
		        			String filtered2 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getMother().toString());
		        			String filtered3 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getBrother().toString());
		        			String filtered4 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getSister().toString());
		        			String filtered5 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getSon().toString());
		        			String filtered6 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getDaughter().toString());
							player.sendMessage("Famille :");
							player.sendMessage("Pères: " +  filtered1 + " | Mères: " + filtered2);
							player.sendMessage("Frères: " + filtered3 + " | Soeurs: " + filtered4);
							player.sendMessage("Fils: " + filtered5 + " | Filles: " + filtered6);
		            		}
		        		else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé");
		        		}
		        	}
		        	else if (args.length == 2) {
		        		final File file2 = new File(savedir, args[1] + ".json");
		            	if(file2.exists()) {
		            		final String json = FileUtils.loadContent(file2);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			String filtered1 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getFather().toString());
		        			String filtered2 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getMother().toString());
		        			String filtered3 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getBrother().toString());
		        			String filtered4 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getSister().toString());
		        			String filtered5 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getSon().toString());
		        			String filtered6 = CharMatcher.anyOf(charsToRemove).removeFrom(profile.getDaughter().toString());
							player.sendMessage("Famille :");
							player.sendMessage("Pères: " +  filtered1 + " | Mères: " + filtered2);
							player.sendMessage("Frères: " + filtered3 + " | Soeurs: " + filtered4);
							player.sendMessage("Fils: " + filtered5 + " | Filles: " + filtered6);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), "Unkown", "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        			player.sendMessage(ChatColor.GREEN + "Profil créé");
		        			
		        		}
					}
		        }
		        return false;
		    }
				
	

		    @Override
		    public List<String> onTabComplete(final CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
		        if (args.length == 1) return Arrays.asList("see","mother", "father", "sister", "brother", "son", "daugther");
		        if (args.length == 2)  return Arrays.asList("add", "clear");
				return null; 
		        }
		    
		    

	

}
