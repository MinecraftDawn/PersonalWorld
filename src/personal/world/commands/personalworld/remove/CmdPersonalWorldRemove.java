package personal.world.commands.personalworld.remove;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import personal.world.commands.IPersonalCommand;
import personal.world.permission.PermissionManager;

public class CmdPersonalWorldRemove implements IPersonalCommand{
	
	private PermissionManager yml = PermissionManager.getInstance();

	/***********************************************************
	 * Remove personal world permission
	 ***********************************************************/
	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		if(!sender.hasPermission("personalworld.remove")){ 
			
			msg.sendMsg(sender, "PermissionDenied");
			
			return;
		}
		
		if(args.length < 2) {
			
			msg.sendMsg(sender, "ParameterNotEnough");
			
			return;
		}
		
		Player p = (Player) sender;
		
		Player target = Bukkit.getPlayerExact(args[1]);
		
		if(target == null) {
			
			sender.sendMessage("找不到玩家" + args[1]);
			
			return;
		}
		
		if(yml.removePermission(p, target.getName())){
			sender.sendMessage("成功");
		}else{
			sender.sendMessage("失敗");
		}
		
	}

}
