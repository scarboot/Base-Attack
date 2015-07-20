package base_attack;

import java.lang.reflect.Constructor;
import java.util.Random;

public class MobCreator {
	
	private final Constructor<? extends Mob> constructor;
	private final int value;
	private final double spawnRate;
	
	public MobCreator(Class<? extends Mob> clazz, double spwanRate) {
		
		this.spawnRate = spwanRate;
		
		try {
			
			constructor = clazz.getDeclaredConstructor(Path.class);
			value = clazz.getDeclaredField("VALUE").getInt(null);
			
		} catch (Exception e) {
			throw new RuntimeException("Error while initiating the MobCreator for " + clazz.getSimpleName(), e);
		}
		
	}
	
	public Mob create(Path path) {
		
		try {
			return constructor.newInstance(path);
		} catch (Exception e) {
			throw new RuntimeException("Error while calling " + constructor.getName(), e);
		}
		
	}
	
	public int getValue() {
		return value;
	}
	
	public double getSpawnRate() {
		return spawnRate;
	}
	
	public boolean spawn(Random r) {
		return r.nextDouble() < getSpawnRate();
	}

}
