package personal.world.message;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;

public class MessageManager {
	
	private Plugin plugin;
	
	private static MessageManager instance;
	
	private File Message;
	
	private String language;
	
	private MessageManager() {
		plugin = PersonalWorld.plugin;

		language = plugin.getConfig().getString("Language");
		
		Message = new File(plugin.getDataFolder(), language);
		
		if(! Message.exists()){
			
			Message = new File(plugin.getDataFolder(), "Chinese.yml");
		}
		
	}
	
	public static MessageManager getInstance(){
		if(instance == null){
			
			instance = new MessageManager();
		}
		
		return instance;
	}
	
	public void sendMsg(Player p,String msg){
		msg = symbolToColor(msg);
		
		p.sendMessage(msg);
	}

	public void sendMsg(CommandSender sender,String msg){
		msg = symbolToColor(msg);
		
		sender.sendMessage(msg);
	}
	
	private String symbolToColor(String msg){
		msg.replace('&','§');
		
		return msg;
	}
	

}
