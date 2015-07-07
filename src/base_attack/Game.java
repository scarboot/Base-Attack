package base_attack;

import base_attack.ui.Frame;

public class Game {
	
	private final Map map = MapGenerator.generateMap();
	private final MobSpawner spawner = new MobSpawner(this);
	
	public Game() {
	}
	
	// End of useful code / main
	
	public static void main(String[] args) {
		
		final Game game = new Game();
		final Frame f = new Frame(game, MapGenerator.X*Tile.SIZE, MapGenerator.Y*Tile.SIZE);
		
		while(true)
			f.draw();
		
	}
	
	public Map getMap() {
		return map;
	}

	public MobSpawner getSpawner() {
		return spawner;
	}
	
}
