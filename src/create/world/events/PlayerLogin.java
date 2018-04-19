package create.world.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import create.world.Main;
import create.world.PersonalWorldCreator;

public class PlayerLogin implements Listener{
	
	private final Plugin plugin = Main.plugin;
	
	private String worldPath;
	
	public PlayerLogin() {
		worldPath = plugin.getConfig().getString("WorldPath");
	}

	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent e){
		if(plugin.getConfig().getBoolean("AutoCreateWorld")){
			String subPath = worldPath + "/" + e.getPlayer().getUniqueId().toString();
			
			WorldCreator perWorld = new WorldCreator(subPath);
			
			if(plugin.getConfig().getBoolean("CopyWorld.Enable")){ //If copy world option was enable
				World copiedWorld =  Bukkit.getWorld(plugin.getConfig().getString("CopyWorld.CopyWorldName"));
				
				perWorld.copy(copiedWorld);
			}
			
			PersonalWorldCreator creator = new PersonalWorldCreator(perWorld);
			Thread worldThread = new Thread(creator);
			
			worldThread.setPriority(Thread.MIN_PRIORITY);
			worldThread.start();
		}
	}
}
