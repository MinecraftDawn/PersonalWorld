package personal.world.commands.personalworld;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;
import personal.world.commands.IPersonalCommand;

public class CmdPersonalWorld implements IPersonalCommand{
	private final Plugin plugin = PersonalWorld.plugin;
	
	private String worldPath = plugin.getConfig().getString("WorldPath");

	
	/***********************************************************
	 * Teleport command sender to his personal world 
	 ***********************************************************/
	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		if(! sender.hasPermission("personalworld.tp")){
			
			sender.sendMessage("你沒權限");
			
			return;
		}
		
		Player p = (Player) sender;
		
		if(args.length == 0){
			
			String subPath = worldPath + "/" + p.getUniqueId().toString();
			
			Location loc = new Location(Bukkit.getWorld(subPath), 100, 100, 100);
			
			p.teleport(loc);
		}
		return;
		
	}

}
