package me.angelfire.rpfamily;

import org.bukkit.plugin.java.JavaPlugin;

import me.angelfire.rpfamily.commands.Rpf;
import me.angelfire.rpfamily.json.ProfileSerializationManager;


public final class RpFamily extends JavaPlugin {
	
	public static RpFamily INSTANCE;
	private ProfileSerializationManager profileSerializationManager;
	

    @Override
    public void onEnable() {;
    	INSTANCE  = this;
    	this.profileSerializationManager  = new ProfileSerializationManager();

    	getCommand("rpfamily").setExecutor(new Rpf(this));

    }


	@Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    


	public void setProfileSerializationManager(ProfileSerializationManager profileSerializationManager) {
		this.profileSerializationManager = profileSerializationManager;
	}


	public ProfileSerializationManager getProfileSerializationManager() {
		return profileSerializationManager;
	}
}
