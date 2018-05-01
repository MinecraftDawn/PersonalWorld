package personal.world;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import personal.world.commands.CmdAdminManager;
import personal.world.commands.CmdManager;
import personal.world.events.PlayerLogin;
import personal.world.events.PlayerLogout;
import personal.world.file.manager.FileManager;

public class PersonalWorld extends JavaPlugin{
	
	public static Plugin plugin;
	
	public void onEnable(){
		plugin = this;
		
		/***********************************************************
		 * If "plugin.yml" not exists,copy one in datafolder
		 ***********************************************************/
		if(!new File(getDataFolder(), "config.yml").exists()){
			
			saveDefaultConfig();
			
			reloadConfig(); 
		} 

		
		/***********************************************************
		 * If directory not exists,create one encase personal world 
		 ***********************************************************/
		File path = new File("./"+ getConfig().getString("WorldPath"));
		
		if(!path.exists()) path.mkdirs();
		
		
		/***********************************************************
		 * Register events 
		 ***********************************************************/
		Bukkit.getPluginManager().registerEvents(new PlayerLogin() , this);
		Bukkit.getPluginManager().registerEvents(new PlayerLogout(), this);
		
		
		/***********************************************************
		 * Register commands
		 ***********************************************************/
		Bukkit.getPluginCommand("personalworld").setExecutor((CommandExecutor) new CmdManager());
		
		Bukkit.getPluginCommand("personalworldadmin").setExecutor((CommandExecutor) new CmdAdminManager());
		
	}

}
