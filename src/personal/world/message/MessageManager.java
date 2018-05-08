package personal.world.message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;

public class MessageManager {
	
	private Plugin plugin;
	
	private static MessageManager instance;
	
	private File Message;
	
	private YamlConfiguration msgData;
	
	private String language;
	
	private String prefix;
	
	private MessageManager() {
		plugin = PersonalWorld.plugin;

		language = plugin.getConfig().getString("Language");
		
		msgData = new YamlConfiguration();
		
		Message = new File(plugin.getDataFolder(), language);
		
		if(! Message.exists()){
			
			Message = new File(plugin.getDataFolder(), "Chinese.yml");
			
			if(! Message.exists())

				plugin.saveResource("Chinese.yml", false);
			
		}
		
		prefix = plugin.getConfig().getString("Prefix");
		
		loadData();
	}
	
	public static MessageManager getInstance(){
		
		if(instance == null){
			
			instance = new MessageManager();
			
		}
		
		return instance;
	}
	
	private void loadData(){
		try {
			msgData.load(Message);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg(Player p,String config){
		
		String msg = getConfig(config);

		msg = symbolToColor(msg);
		
		p.sendMessage(msg);
	}

	public void sendMsg(CommandSender sender,String config){
		
		String msg = getConfig(config);

		msg = symbolToColor(msg);
		
		
		sender.sendMessage(msg);
	}
	
	private String symbolToColor(String msg){
		msg = prefix + msg;
		
		msg = msg.replace('&','§');
		
		return msg;
	}

	public String getConfig(String config){
		loadData();
		
		if(msgData.getString(config) != null)
			return msgData.getString(config);
		
		else 
			return "&4 '" + config + "' &fNot set";
	}
	
}
