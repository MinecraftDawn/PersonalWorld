package create.world;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import create.world.events.PlayerLogin;
import create.world.commands.MyWorld;

public class Main extends JavaPlugin{
	
	public static Plugin plugin;
	
	public void onEnable(){
		plugin = this;
		
		/***********************************************************
		 * If "plugin.yml" not exists,copy one in dataolder 
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
		Bukkit.getPluginManager().registerEvents(new PlayerLogin(), this);
		
		/***********************************************************
		 * Register commands
		 ***********************************************************/
		Bukkit.getPluginCommand("myworld").setExecutor((CommandExecutor) new MyWorld());
		
	}

}
