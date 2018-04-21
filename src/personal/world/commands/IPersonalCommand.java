package personal.world.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface IPersonalCommand {
	
	void run(CommandSender sender, Command cmd, String[] args);
		
}
