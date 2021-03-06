package personal.world.commands.personalworldadmin.remove;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import personal.world.commands.IPersonalCommand;
import personal.world.permission.PermissionManager;

public class CmdPersonalWorldAdminRemove implements IPersonalCommand{
	
	private PermissionManager yml = PermissionManager.getInstance();

	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		if(args.length < 3) {
			
			msg.sendMsg(sender, "ParameterNotEnough");
			
			return;
		}
		
		OfflinePlayer pmsWorld = Bukkit.getOfflinePlayer(args[1]);
		
		if(pmsWorld  == null || ! pmsWorld.hasPlayedBefore() || ! yml.hasWorld(pmsWorld.getUniqueId().toString())) {
			
			sender.sendMessage("找不到世界" + args[1]);
			
			return; 
		}
		
		OfflinePlayer target = Bukkit.getOfflinePlayer(args[2]);
		
		if(target == null) {
			
			sender.sendMessage("找不到玩家" + args[1]);
			
			return;
		}
		
		if(yml.removePermission(pmsWorld, target.getName())){
			msg.sendMsg(sender, "PermissionEditSuccess");
		}else{
			msg.sendMsg(sender, "PermissionEditFail");
		}
	}

}
