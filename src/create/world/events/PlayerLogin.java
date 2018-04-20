package create.world.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import create.world.CreateWorld;
import create.world.PersonalWorldCreator;
import create.world.file.manager.FileManager;

public class PlayerLogin implements Listener{
	
	private final Plugin plugin = CreateWorld.plugin;
	
	private String worldPath;
	
	public PlayerLogin() {
		worldPath = plugin.getConfig().getString("WorldPath");
	}

	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent e){
		
		if(plugin.getConfig().getBoolean("AutoCreateWorld")){ //If auto create world option was enable
			
			String subPath = worldPath + "/" + e.getPlayer().getUniqueId().toString();
			
			WorldCreator perWorld = new WorldCreator(subPath);
			
			if(plugin.getConfig().getBoolean("CopyWorld.Enable")){ //If copy world option was enable
				
				World copiedWorld =  Bukkit.getWorld(plugin.getConfig().getString("CopyWorld.CopyWorldName"));
				
				perWorld.copy(copiedWorld);
			}
			
			PersonalWorldCreator creator = new PersonalWorldCreator(perWorld);
			

			//Make a new thread to create personal world 
			Thread worldThread = new Thread(creator);
			
			worldThread.setPriority(Thread.MIN_PRIORITY);
			
			worldThread.start();
			
			
			//add player to permission file
			FileManager manager = new FileManager();
			
			manager.createPremission(e.getPlayer());
		}
	}
}
