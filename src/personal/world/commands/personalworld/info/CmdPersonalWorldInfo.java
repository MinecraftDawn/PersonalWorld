package personal.world.commands.personalworld.info;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import personal.world.commands.IPersonalCommand;
import personal.world.permission.PermissionManager;

public class CmdPersonalWorldInfo implements IPersonalCommand{
	
	private PermissionManager yml = PermissionManager.getInstance();

	
	/***********************************************************
	 * Get who has sender's personal world permission
	 ***********************************************************/
	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		msg.sendMsg(sender, "&1你沒系統權限");
		
//		System.out.println(msg.getConfig());
		
		if(!sender.hasPermission("personalworld.info")){ //have not permission of /pw info
			
			msg.sendMsg(sender, "你沒系統權限");
			
//			sender.sendMessage("你沒系統權限");
			
			return;
		}
		
		Player p = (Player) sender;
		
		for(String str : yml.getPmsYmlStrList(p.getUniqueId().toString(),"Permission")){
			
			UUID uuid = UUID.fromString(str);
			
			OfflinePlayer offplayer = Bukkit.getOfflinePlayer(uuid);
			
			if(offplayer.hasPlayedBefore()){
				
				sender.sendMessage(offplayer.getName());
			}
			
		}
		
	}

}
