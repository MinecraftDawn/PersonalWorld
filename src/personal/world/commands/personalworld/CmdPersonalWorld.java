package personal.world.commands.personalworld;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;
import personal.world.commands.IPersonalCommand;
import personal.world.file.manager.FileManager;

public class CmdPersonalWorld implements IPersonalCommand{
	
	private FileManager yml = FileManager.getInstance();

	
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
			
		String path = yml.getPersonalWorldPath(p);
		
		Location loc = new Location(Bukkit.getWorld(path), 100, 100, 100);
		
		p.teleport(loc);
		
		return;
		
	}

}
