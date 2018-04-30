package personal.world.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import personal.world.commands.personalworldadmin.info.CmdPersonalWorldAdminInfo;
import personal.world.commands.personalworldadmin.tp.CmdPersonalWorldAdminTp;

public class CmdAdminManager implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!sender.hasPermission("personalworld.admin")){ //have not permission
			
			sender.sendMessage("你沒系統權限");
			
			return true; 
		}
		
		if(args.length == 0) {
			sender.sendMessage("參數不夠");
			
			return true;
		}
		
		if(args[0].equalsIgnoreCase("info")) {
			CmdPersonalWorldAdminInfo info = new CmdPersonalWorldAdminInfo();
			
			info.run(sender, cmd, args);
		}
		
		if(args[0].equalsIgnoreCase("tp")){
			CmdPersonalWorldAdminTp tp = new CmdPersonalWorldAdminTp();
			
			tp.run(sender, cmd, args);
		}
		return true;
	}

}
