package personal.world.commands.personalworld.tp;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;
import personal.world.commands.IPersonalCommand;
import personal.world.commands.personalworld.CmdPersonalWorld;
import personal.world.file.manager.FileManager;

public class CmdPersonalWorldTp implements IPersonalCommand{
	
	private final Plugin plugin = PersonalWorld.plugin;
	
	private String worldPath = plugin.getConfig().getString("WorldPath");
	
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
		
		if(!sender.hasPermission("personalworld.tp.other")){
			
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
		
		UUID tpworld = Bukkit.getServer().getPlayer(args[1]).getUniqueId();
		
		if(! Bukkit.getServer().getWorlds().contains(tpworld)){
			
			sender.sendMessage("不存在的世界： " + args[1]);
			
			return;
		}
		
		String subPath = worldPath + "/" + tpworld.toString();
		
		Location loc = new Location(Bukkit.getWorld(subPath), 100, 100, 100);
		
		p.teleport(loc);
	}

}







