package personal.world.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import personal.world.commands.personalworld.CmdPersonalWorld;
import personal.world.commands.personalworld.info.CmdPersonalWorldInfo;
import personal.world.commands.personalworld.tp.CmdPersonalWorldTp;

public class CmdManager implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("PW") || label.equalsIgnoreCase("PersonalWorld")){ //pw
			
			if(args.length == 0){
				CmdPersonalWorld PW = new CmdPersonalWorld();
				
				PW.run(sender, cmd, args);
				
				return true;
				
			}		
			
			if(args[0].equalsIgnoreCase("tp")){ //pw tp
				if(args.length == 1){
					
					sender.sendMessage("參數不夠");
					
					return true;
				}
				
				CmdPersonalWorldTp tp = new CmdPersonalWorldTp();
				
				tp.run(sender, cmd, args);
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("info")){ //pw info
				if(args.length == 1){
					
					CmdPersonalWorldInfo info = new CmdPersonalWorldInfo();
					
					info.run(sender, cmd, args);
					
					return true;
				}
			}
			
		}
		return true;
	}

}
