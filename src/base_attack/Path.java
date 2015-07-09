package base_attack;

import java.util.List;

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
	
	public static final Path createPath(List<Tile> tiles) {
		
		return new Path(tiles.toArray(new Tile[tiles.size()]));
		
	}
	
	public static final Path createReversedPath(List<Tile> tiles) {
		
		final Tile[] tileArray = new Tile[tiles.size()];
		
		for(int i = 0; i < tiles.size(); i++)
			tileArray[i] = tiles.get(tiles.size() - 1 - i);
		
		return new Path(tileArray);
		
	}

}
