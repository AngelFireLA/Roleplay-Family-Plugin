package me.angelfire.rpfamily.commands;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
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
    	if(file.exists()) {
			
			final String json = FileUtils.loadContent(file);
			final Profile profile = profileSerializationManager.deserialize(json);
    		}
    	if (args.length == 0) return false;
    	if (args[0].equalsIgnoreCase("mother")) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), args[2].toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), "None", profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        
		        if (args[0].equalsIgnoreCase("father") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), args[2].toString(), profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), "None", profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        if (args[0].equalsIgnoreCase("sister") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), args[2].toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), profile.getBrother().toString(), "None");
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        if (args[0].equalsIgnoreCase("brother") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), args[2].toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), profile.getDaughter().toString(), "None", profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        if (args[0].equalsIgnoreCase("son") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), profile.getFather().toString(), args[2].toString(), profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);
		        			FileUtils.save(file1, json);
		        			
		        		}
		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), profile.getFather().toString(), "None", profile.getDaughter().toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
		            }
		        }
		        if (args[0].equalsIgnoreCase("daugter") && args.length >= 3) {
		            if(args[1].equalsIgnoreCase("add")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), args[2].toString(), profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else if(args[1].equalsIgnoreCase("clear")) {
		            	if(file.exists()) {
		        			
		        			final String json = FileUtils.loadContent(file);
		        			final Profile profile = profileSerializationManager.deserialize(json);
		        			final Profile profile1 = Profile.createProfile(player.getName(), player.getName(), profile.getMother().toString(), profile.getFather().toString(), profile.getSon().toString(), "None", profile.getBrother().toString(), profile.getSister().toString());
		        			final String json1 = profileSerializationManager.serialize(profile1);
		        			FileUtils.save(file, json1);
		            	}
		            	else {
		        			player.sendMessage(ChatColor.RED + "Profil non existant ! Création en cours...");
		        			final File file1 = new File(savedir, player.getName() + ".json");	
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
		        		}
		            }
		            else {
		            	player.sendMessage(ChatColor.RED + "Options : add | clear");
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
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file, json);
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
		        			final Profile profile = Profile.createProfile(player.getName(), player.getName(), "None", "None", "None", "None", "None", "None");
		        			final String json = profileSerializationManager.serialize(profile);

		        			FileUtils.save(file1, json);
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
