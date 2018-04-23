package personal.world.commands.personalworld.add;

import java.lang.annotation.Target;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;
import personal.world.commands.IPersonalCommand;
import personal.world.file.manager.FileManager;

public class CmdPersonalWorldAdd implements IPersonalCommand{
	
	private final Plugin plugin = PersonalWorld.plugin;
	
	private FileManager yml = FileManager.getInstance();

	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		if(args.length < 2) {
			
			sender.sendMessage("參數不夠");
			
			return;
		}
		
		Player p = (Player) sender;
		
		Player target = Bukkit.getPlayerExact(args[0]);
		
		if(target == null) {
			
			sender.sendMessage("不存在" + args[1]);
			
			return;
		}
		
		yml.addPermission(p, target.getName());
	}

}
