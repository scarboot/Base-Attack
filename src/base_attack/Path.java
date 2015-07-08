package base_attack;

public class Path {
	
	private final Tile[] tiles;
	
	/**
	 * 
	 * @param tiles - The tiles of the path beginning with the first one and ending with the last one
	 */
	public Path(Tile[] tiles) {
		this.tiles = tiles;
	}
	
	public Tile getTile(int index) {		
		return index < getLength() ? tiles[index] : null;
	}
	
	public int getLength() {
		return tiles.length;
	}

}
