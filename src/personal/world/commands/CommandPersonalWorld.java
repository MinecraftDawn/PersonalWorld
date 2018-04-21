package personal.world.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;

public class CommandPersonalWorld implements IPersonalCommand{
	private final Plugin plugin = PersonalWorld.plugin;
	
	private String worldPath = plugin.getConfig().getString("WorldPath");

	
	/***********************************************************
	 * Teleport command sender to his personal world 
	 ***********************************************************/
	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		Player p = (Player) sender;
		
		if(args.length == 0){
			
			String subPath = worldPath + "/" + p.getUniqueId().toString();
			
			Location loc = new Location(Bukkit.getWorld(subPath), 100, 100, 100);
			
			p.teleport(loc);
		}
		return;
		
	}

}
