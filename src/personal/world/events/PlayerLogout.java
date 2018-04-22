package personal.world.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;

public class PlayerLogout implements Listener{
	
	private Plugin plugin = PersonalWorld.plugin;
	
	private String worldPath;
	
	public PlayerLogout() {
		worldPath = plugin.getConfig().getString("WorldPath");
	}
	
	@EventHandler
	public void onPlayerLogoutEvent(PlayerQuitEvent e){ 
		
		if(plugin.getConfig().getBoolean("AutoUnloadWorld")){ //unload personal when the player logout
			
			String subPath = worldPath + "/" + e.getPlayer().getUniqueId().toString();
			
			World unloadWorld = Bukkit.getServer().getWorld(subPath);
			
			if(Bukkit.getServer().getWorlds().contains(unloadWorld)){ 
				
				Bukkit.unloadWorld(subPath, true);
				
			}
			
		}
		
	}

}
