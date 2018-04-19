package create.world;

import org.bukkit.WorldCreator;


public class PersonalWorldCreator implements Runnable{
	
	private WorldCreator world;
	
	public PersonalWorldCreator(WorldCreator world) {
		this.world = world;
	}

	@Override
	public void run() {
		try{
			world.createWorld();
		}catch (Exception e) {
		}

	}
}
