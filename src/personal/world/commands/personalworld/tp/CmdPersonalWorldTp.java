package personal.world.commands.personalworld.tp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;
import personal.world.commands.IPersonalCommand;
import personal.world.commands.personalworld.CmdPersonalWorld;
import personal.world.permission.PermissionManager;

public class CmdPersonalWorldTp implements IPersonalCommand{
	
	private PermissionManager yml = PermissionManager.getInstance();
	
	/***********************************************************
	 * Teleport command sender to other's personal world 
	 ***********************************************************/
	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		Player p = (Player) sender;
		
		Player target = Bukkit.getPlayerExact(args[1]);
		
		if(sender.equals(target)){
			
			CmdPersonalWorld PW = new CmdPersonalWorld();
			
			PW.run(sender, cmd, args);
			
			return;
		}
		
		if(!sender.hasPermission("personalworld.tpOther")){ //have not permission of /pw tp <world>
			
			sender.sendMessage("你沒系統權限");
			
			return;
		}
		
		if(target == null){
			
			sender.sendMessage("不存在" + args[1]);
			
			return;
		}
		
		if(! yml.getPmsYmlStrList(target.getUniqueId().toString(),"Permission").contains(p.getUniqueId().toString())){
			
			sender.sendMessage("你沒該世界權限");
			
			return;
		}
		
		String path = yml.getPersonalWorldPath(p);
		
		World tarWorld = Bukkit.getWorld(path);
		
		if(! Bukkit.getServer().getWorlds().contains(tarWorld)){
			
			sender.sendMessage("不存在的世界： " + args[1]);
			
			return;
		}
		
		Location loc = new Location(Bukkit.getWorld(path), 100, 100, 100);
		
		p.teleport(loc);
	}

}








