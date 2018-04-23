package personal.world.commands.personalworld.info;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;
import personal.world.commands.IPersonalCommand;
import personal.world.file.manager.FileManager;

public class CmdPersonalWorldInfo implements IPersonalCommand{
	
	private FileManager yml = FileManager.getInstance();

	
	/***********************************************************
	 * Get who has sender's personal world permission
	 ***********************************************************/
	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		if(!sender.hasPermission("personalworld.remove")){
			
			sender.sendMessage("你沒系統權限");
			
			return;
		}
		
		Player p = (Player) sender;
		
		String world  = yml.getPersonalWorldPath(p);
		
		World searchWorld = Bukkit.getWorld(world);
		
		if(Bukkit.getWorlds().contains(searchWorld)){
			
			for(String str : yml.getPmsYmlStrList(p.getUniqueId().toString(),"Permission")){
				
				UUID uuid = UUID.fromString(str);
				
				OfflinePlayer offplayer = Bukkit.getOfflinePlayer(uuid);
				
				if(offplayer.hasPlayedBefore()){
					
					sender.sendMessage(offplayer.getName());
				}
				
			}
		}
	}

}
