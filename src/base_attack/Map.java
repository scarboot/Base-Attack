package base_attack;

import java.util.*;

public class Map implements Updateable {
	
	private final Tile[][] fields;
	private final List<Mob> mobs = new ArrayList<Mob>();
	private final List<Bullet> bullets = new ArrayList<Bullet>();
	private Path mobPath;
	
	public Map(int width, int height) {
		this.fields = new Tile[width][height];
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				getTiles()[x][y] = new Tile(x, y);
	}

	public Tile[][] getTiles() {
		return fields;
	}

	public boolean setTile(int x, int y, TileType type){
		if(fields[x][y] == null)
			return false;
		fields[x][y].setType(type);
		return true;
	}

	public boolean setTower(int x, int y, Tower newTower) {
		if(fields[x][y].hasTower())
			return false;
		fields[x][y].setTower(newTower);
		return true;
	}

	public List<Mob> getMobs() {
		return mobs;
	}

	public Path getMobPath() {
		return mobPath;
	}

	public void setMobPath(Path mobPath) {
		this.mobPath = mobPath;
	}

	@Override
	public void update(double t) {
		
		final Iterator<Mob> mobIterator = mobs.iterator();
		
		while(mobIterator.hasNext()) {
			
			final Mob m = mobIterator.next();
			
			m.update(t);
			
			if(m.isDead())
				mobIterator.remove();
			
		}
		
	}

	public List<Bullet> getBullets() {
		return bullets;
	}
}