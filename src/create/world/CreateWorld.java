package create.world;

import java.io.File;
import java.nio.file.Path;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CreateWorld extends JavaPlugin{
	
	public void onEnable(){		
		if(!new File(getDataFolder(), "config.yml").exists()){
			saveDefaultConfig();
			reloadConfig();
		}
		
		File house = new File("./house");
		if(!house.exists())house.mkdir();
		
		WorldCreator myWorld = new WorldCreator("./house/testWorld");
		myWorld.copy(Bukkit.getWorld("world_nether"));
		myWorld.createWorld();
	}
	
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args){
		if(args.length==0){
			if(Bukkit.getWorlds().toString().contains("./house/testWorld")){
				Player p = (Player) sender;
				Location loc = new Location(Bukkit.getWorld("./house/testWorld"), 100, 100, 100);
				p.teleport(loc);
			}else sender.sendMessage("世界不存在唷！");
			
		}else if(args.length==1){
			Bukkit.unloadWorld("./house/testWorld", true);
			
		}else if(args.length==2){
			WorldCreator myWorld = new WorldCreator("./house/testWorld").environment(Environment.NETHER);
			myWorld.createWorld();
			
		}else if(args.length==3){
			getLogger().info(Bukkit.getWorld("./house/testWorld").getUID().toString());
		}
		return true;
	}

}
