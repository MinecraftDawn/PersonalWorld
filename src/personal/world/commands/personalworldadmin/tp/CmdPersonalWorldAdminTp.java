package personal.world.commands.personalworldadmin.tp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import personal.world.commands.IPersonalCommand;
import personal.world.commands.personalworld.CmdPersonalWorld;
import personal.world.file.FileManager;

public class CmdPersonalWorldAdminTp implements IPersonalCommand{
	
	private FileManager yml = FileManager.getInstance();

	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		Player p = (Player) sender;
		
		Player target = Bukkit.getPlayerExact(args[1]);
		
		if(sender.equals(target)){
			
			CmdPersonalWorld PW = new CmdPersonalWorld();
			
			PW.run(sender, cmd, args);
			
			return;
		}
		
		if(target == null){
			
			sender.sendMessage("不存在" + args[1]);
			
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
