package base_attack;

import java.util.*;

public class Map {
	
	Tile[][] fields;
	List<Tower> towers = new ArrayList<Tower>();
	
	public Map(int height, int width) {
		this.fields = new Tile[height][width];
	}

	public boolean setTile(int x, int y, TileType type){
		if(fields[x][y] != null)
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
}