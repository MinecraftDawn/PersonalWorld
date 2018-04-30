package personal.world.commands.personalworldadmin.add;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import personal.world.commands.IPersonalCommand;
import personal.world.file.manager.FileManager;

public class CmdPersonalWorldAdminAdd implements IPersonalCommand{
	
	private FileManager yml = FileManager.getInstance();

	@Override
	public void run(CommandSender sender, Command cmd, String[] args) {
		
		if(args.length < 2) {
			
			sender.sendMessage("參數不夠");
			
			return;
		}
		
		Player p = (Player) sender;
		
		Player target = Bukkit.getPlayerExact(args[1]);
		
		if(target == null) {
			
			sender.sendMessage("找不到玩家" + args[1]);
			
			return;
		}
		
		if(yml.addPermission(p, target.getName())){
			sender.sendMessage("成功");
		}else{
			sender.sendMessage("失敗");
		}		
	}

}
