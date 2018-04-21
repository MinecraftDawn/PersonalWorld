package create.world.file.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import create.world.CreateWorld;

public class FileManager {
	private File permission =null;
	
	private YamlConfiguration pmsData = new YamlConfiguration();
	
	private Plugin plugin = CreateWorld.plugin;
	
	private void loadData(){
		try {
			pmsData.load(permission);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private void saveData(){
		try {
			pmsData.save(permission);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setYmlFile(YamlConfiguration yml,String ... args){ //setYmlFile( ymlFile , args[1] , args[2] , ... , data)\
		String field = args[0];
		
		String data = args[args.length-1];
		
		for(int i = 1; i < args.length-1 ; i++){
			field += "." + args[i] ;
		}
		
		yml.set(field, data);
		
		saveData();
	}
	
	public FileManager(){
		permission = new File(plugin.getDataFolder(), "Permissions.yml");
		
		if(!permission.exists()){
			plugin.saveResource("Permissions.yml", false);
		}
		
		loadData();
		
	}
	
	public void createPremission(Player p){
		String uuid = p.getUniqueId().toString();
		
		String name = p.getName();
		
		setYmlFile(pmsData,uuid,"Owner",name);
		
		
		
	}
	
}
