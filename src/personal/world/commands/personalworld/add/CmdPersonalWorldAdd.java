package personal.world.commands.personalworld.add;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import personal.world.commands.IPersonalCommand;
import personal.world.permission.PermissionManager;

public class CmdPersonalWorldAdd implements IPersonalCommand{
	
	private PermissionManager yml = PermissionManager.getInstance();

	/***********************************************************
	 * Add personal world permission
	 ***********************************************************/
	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		if(!sender.hasPermission("personalworld.add")){ //have not permission of /pw add
			
			msg.sendMsg(sender, "PermissionDenied");
			
			return;
		}
		
		if(args.length < 2) {
			
			msg.sendMsg(sender, "ParameterNotEnough");
			
			return;
		}
		
		Player p = (Player) sender;
		
		OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
		
		if(target == null) {
			
			sender.sendMessage("找不到玩家" + args[1]);
			
			return;
		}
		
		if(yml.addPermission(p, target.getName())){
			sender.sendMessage("成功");
		}else{
			sender.sendMessage("失敗");
		}
		
	}

}
