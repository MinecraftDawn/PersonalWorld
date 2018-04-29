package personal.world.commands.personalworldadmin.info;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import personal.world.commands.IPersonalCommand;
import personal.world.file.manager.FileManager;

public class CmdPersonalWorldAdminInfo implements IPersonalCommand{
	
	private FileManager yml = FileManager.getInstance();

	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
		
		if(target == null) {
			
			sender.sendMessage("找不到玩家" + args[1]);
			
			return; 
		}
		
		sender.sendMessage(target.getUniqueId().toString());
		
		for(String str : yml.getPmsYmlStrList(target.getUniqueId().toString(),"Permission")){
			
			UUID uuid = UUID.fromString(str);
			
			OfflinePlayer offplayer = Bukkit.getOfflinePlayer(uuid);
			
			if(offplayer.hasPlayedBefore()){
				
				sender.sendMessage(offplayer.getName());
			}
			
		}	
	}

}
