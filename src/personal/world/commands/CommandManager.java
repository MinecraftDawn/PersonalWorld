package personal.world.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("PW") || label.equalsIgnoreCase("PersonalWorld")){
			
			if(args.length == 0){
				CommandPersonalWorld PW = new CommandPersonalWorld();
				
				PW.run(sender, cmd, args);
				
			}else if(args.length == 1){
				
				
			}
			
			
		}
		return true;
	}

}
