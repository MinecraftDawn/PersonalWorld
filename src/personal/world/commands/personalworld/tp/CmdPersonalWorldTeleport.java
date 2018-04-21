package personal.world.commands.personalworld.tp;

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
		
		String tpworld = Bukkit.getServer().getPlayer(args[1]).getUniqueId().toString();
		
		String subPath = worldPath + "/" + tpworld;
		
		Location loc = new Location(Bukkit.getWorld(subPath), 100, 100, 100);
		
		Player p = (Player) sender;
		
		p.teleport(loc);
		sender.sendMessage("wwww");
		
	}

}








