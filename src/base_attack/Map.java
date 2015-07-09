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
		
		{//UPDATE TOWERS
			
			for(Tile[] tiles: getTiles()) for(Tile tile: tiles)
				if(tile.hasTower())
					tile.getTower().update(t);
			
		}
		
		{//UPDATE BULLETS
			
			final Iterator<Bullet> it = getBullets().iterator();
			
			while(it.hasNext()) {
				
				final Bullet b = it.next();
				
				if(b.update(t))
					it.remove();
				
			}
			
		}
		
		{//UPDATE MOBS
			
			final Iterator<Mob> it = getMobs().iterator();
			
			while(it.hasNext()) {
				
				final Mob m = it.next();
				
				m.update(t);
				
				if(m.isDead())
					it.remove();
				
			}
			
		}
		
	}

	public List<Bullet> getBullets() {
		return bullets;
	}
}