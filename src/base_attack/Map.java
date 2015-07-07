package base_attack;

import java.util.*;

public class Map {
	
	private final Tile[][] fields;
	private final List<Mob> mobs = new ArrayList<Mob>();
	
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
}