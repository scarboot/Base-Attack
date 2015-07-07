package base_attack;

import base_attack.ui.Frame;

public class Game {
	
	private final Map map = MapGenerator.generateMap();
	
	public Game() {
	}
	
	// End of useful code / main
	
	public static void main(String[] args) {
		Game game = new Game();
		Frame f = new Frame(game, MapGenerator.X*50, MapGenerator.Y*50);
		while(true)
			f.draw();
	}
	
	public Map getMap() {
		return map;
	}
	
}
