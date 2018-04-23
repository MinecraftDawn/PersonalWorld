package personal.world.file.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import personal.world.PersonalWorld;

public class FileManager {
	private File permission =null;
	
	private YamlConfiguration pmsData = new YamlConfiguration();
	
	private Plugin plugin = PersonalWorld.plugin;
	
	private static FileManager instance = new FileManager();
	
	private String worldPath = plugin.getConfig().getString("WorldPath");
	
	public static FileManager getInstance(){
		return instance;		
	}
	
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
	
	private String arrToStr(String[] args){
		
		String field = args[0];
		
		for(int i = 1; i < args.length ; i++){
			
			field += "." + args[i];
		}
		
		return field;
	}
	
	public String getPersonalWorldPath(Player p){
		loadData();
		
		return (worldPath + "/" + p.getUniqueId().toString());
	}
	
	public Object getPmsYmlObj(String ... args){ 
		loadData();
		
		return pmsData.get(arrToStr(args));
	}
	
	public Boolean getPmsYmlBool(String ... args){ 
		loadData();

		return pmsData.getBoolean(arrToStr(args));
	}
	
	public String getPmsYmlStr(String ... args){ 
		loadData();
		
		return pmsData.getString(arrToStr(args));
	}
	
	public List<String> getPmsYmlStrList(String ... args){ 
		loadData();
		
		return pmsData.getStringList(arrToStr(args));
	}
	
	public Boolean addPermission(Player owner,String target) {
		YamlConfiguration yml = pmsData;
		
		String path = owner.getUniqueId() + ".Permission";
		
		List<String> list = yml.getStringList(path);
		
		Player p = Bukkit.getPlayerExact(target);
		
		if(p != null){
			
			if(yml.getStringList(path).contains(p.getUniqueId().toString())){
				
				return false;
			}
			
			list.add(p.getUniqueId().toString());
			
			yml.set(path,list);
			
			saveData();
			
			return true;
		}
		
		return false;
		
	}
	
	public Boolean removePermission(Player owner,String target) {
		YamlConfiguration yml = pmsData;
		
		String path = owner.getUniqueId() + ".Permission";
		
		List<String> list = yml.getStringList(path);
		
		Player p = Bukkit.getPlayerExact(target);
		
		if(p != null){ 
			
			if(! yml.getStringList(path).contains(p.getUniqueId().toString())){
				
				return false;
			}
			
			list.remove(p.getUniqueId().toString());
			
			yml.set(path,list);
			
			saveData();
			
			return true;
			
		}
		
		return false;
		
	}
	
	public void setPmsYml(Object ... args){ //setYmlFile(args[0] , args[1] , args[2] , ... , data)
		YamlConfiguration yml = pmsData;
		
		String field = args[0].toString();
		
		Object data = args[args.length-1];
		
		for(int i = 1; i < args.length-1 ; i++){
			field += "." + args[i].toString() ;
		}
		
		if(data != null)
		
			yml.set(field, data);
		
		else
			
			yml.set(field, null);
		
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
		
		String[] tpPermission = {};
		
		
		if(! pmsData.contains(uuid)){
			
			setPmsYml(uuid,"Owner",name);
		
			setPmsYml(uuid,"Permission",tpPermission);
		}
		
	}
	
}
