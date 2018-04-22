package personal.world.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import personal.world.commands.personalworld.CmdPersonalWorld;
import personal.world.commands.personalworld.tp.CmdPersonalWorldTeleport;

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
				
				CmdPersonalWorldTeleport tp = new CmdPersonalWorldTeleport();
				
				tp.run(sender, cmd, args);
			}
			
		}
		return true;
	}

}
