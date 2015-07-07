package base_attack;

import base_attack.ui.Frame;

public class Game implements Updateable {
	
	private final Map map = MapGenerator.generateMap();
	private final MobSpawner spawner = new MobSpawner(this);
	
	public Game() {
	}
	
	// End of useful code / main
	
	public static void main(String[] args) throws InterruptedException {
		
		final Game game = new Game();
		final Frame f = new Frame(game, MapGenerator.X*Tile.SIZE, MapGenerator.Y*Tile.SIZE);
		
		game.getMap().getMobs().add(new Minion(game.getMap().getMobPath()));
		
		long lastFrame = System.currentTimeMillis();
		
		while(true) {
			
			final long thisFrame = System.currentTimeMillis();
			final double delta = (thisFrame - lastFrame)*0.001; // seconds
			lastFrame = thisFrame;
			
			game.update(delta);
			f.draw();
			Thread.sleep(15);
			
		}
		
	}
	
	public Map getMap() {
		return map;
	}

	public MobSpawner getSpawner() {
		return spawner;
	}

	@Override
	public void update(double t) {
		getMap().update(t);
		getSpawner().update(t);
	}
	
}
