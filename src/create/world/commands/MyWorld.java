package create.world.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import create.world.CreateWorld;

public class MyWorld implements CommandExecutor{
	private final Plugin plugin = CreateWorld.plugin;
	
	private String worldPath = plugin.getConfig().getString("WorldPath");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(args.length == 0){
			String subPath = worldPath + "/" + p.getUniqueId().toString();
			
			Location loc = new Location(Bukkit.getWorld(subPath), 100, 100, 100);
			
			p.teleport(loc);
		}
		return true;
	}

}
