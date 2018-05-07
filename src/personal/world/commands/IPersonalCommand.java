package personal.world.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import personal.world.message.MessageManager;

public interface IPersonalCommand {
	
	MessageManager msg = MessageManager.getInstance();
	
	public void run(CommandSender sender, Command cmd, String[] args);
		
}
