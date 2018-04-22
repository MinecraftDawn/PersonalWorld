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

public class CmdPersonalWorldTeleport implements IPersonalCommand{
	
	private final Plugin plugin = PersonalWorld.plugin;
	
	private String worldPath = plugin.getConfig().getString("WorldPath");

	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		if(!sender.hasPermission("personalworld.tp.other")){
			
			sender.sendMessage("你沒權限");
			
			return;
		}
		
		Player target = Bukkit.getPlayerExact(args[1]);
		
		if(target == null){
			sender.sendMessage("不存在" + args[1]);
			
			return;
		}
		
		UUID tpworld = Bukkit.getServer().getPlayer(args[1]).getUniqueId();
		
		if(! Bukkit.getServer().getWorlds().contains(tpworld)){
			sender.sendMessage("不存在的世界" + args[1]);
			
			return;
		}
		
		String subPath = worldPath + "/" + tpworld.toString();
		
		Location loc = new Location(Bukkit.getWorld(subPath), 100, 100, 100);
		
		Player p = (Player) sender;
		
		p.teleport(loc);
	}

}








