package personal.world.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;
import personal.world.file.FileManager;

public class PlayerLogout implements Listener{
	
	private Plugin plugin = PersonalWorld.plugin;
	
	private FileManager yml = FileManager.getInstance();
	
	@EventHandler
	public void onPlayerLogoutEvent(PlayerQuitEvent e){ 
		
		if(plugin.getConfig().getBoolean("AutoUnloadWorld")){ //unload personal when the player logout
			
			String subPath = yml.getPersonalWorldPath(e.getPlayer());
			
			World unloadWorld = Bukkit.getWorld(subPath);
			
			if(Bukkit.getServer().getWorlds().contains(unloadWorld)){
				
				Bukkit.unloadWorld(subPath, true);
				
			}
			
		}
		
	}

}
