package personal.world.commands.personalworldadmin.add;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import personal.world.commands.IPersonalCommand;
import personal.world.permission.PermissionManager;

public class CmdPersonalWorldAdminAdd implements IPersonalCommand{
	
	private PermissionManager yml = PermissionManager.getInstance();

	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		if(args.length < 3) {
			
			sender.sendMessage("參數不夠");
			
			return;
		}
		
		
		OfflinePlayer pmsWorld = Bukkit.getOfflinePlayer(args[1]);
		
		if(pmsWorld  == null || ! pmsWorld.hasPlayedBefore() || ! yml.hasWorld(pmsWorld.getUniqueId().toString())) {
			
			sender.sendMessage("找不到世界" + args[1]);
			
			return; 
		}
		
		OfflinePlayer target = Bukkit.getOfflinePlayer(args[2]);
		
		if(target == null) {
			
			sender.sendMessage("找不到玩家" + args[2]);
			
			return;
		}
		
		if(yml.addPermission(pmsWorld, target.getName())){
			sender.sendMessage("成功");
		}else{
			sender.sendMessage("失敗");
		}		
	}

}
