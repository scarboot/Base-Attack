package base_attack;

import java.util.*;

public class Map {
	
	Tile[][] fields;
	List<Tower> towers = new ArrayList<Tower>();
	
	public Map(int height, int width) {
		this.fields = new Tile[height][width];
	}

	public Tile[][] getTiles() {
		return fields;
	}
}