package personal.world.commands.personalworld;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import personal.world.commands.IPersonalCommand;
import personal.world.permission.PermissionManager;

public class CmdPersonalWorld implements IPersonalCommand{
	
	private PermissionManager yml = PermissionManager.getInstance();

	
	/***********************************************************
	 * Teleport command sender to his personal world
	 ***********************************************************/
	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		if(! sender.hasPermission("personalworld.tp")){
			
			msg.sendMsg(sender, "ParameterNotEnough");
			
			return;
		}
		
		Player p = (Player) sender;
			
		String path = yml.getPersonalWorldPath(p);
		
		Location loc = new Location(Bukkit.getWorld(path), 100, 100, 100);
		
		p.teleport(loc);
		
		return;
		
	}

}
