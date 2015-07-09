package base_attack;

import base_attack.ui.Frame;

public class Game implements Updateable {
	
	private int money = 0;
	private final Map map = MapGenerator.generateMap();
	private final MobSpawner spawner = new MobSpawner(this);
	
	public Game() {
		getMap().getTiles()[15/2+3][9/2-1].setTower(new SlingTower(this, getMap().getTiles()[15/2+3][9/2-1]));
		getMap().getTiles()[15/2+1][9/2].setTower(new SlingTower(this, getMap().getTiles()[15/2+1][9/2]));
	}

	@Override
	public void update(double t) {
		getMap().update(t);
		getSpawner().update(t);
	}
	
	public Map getMap() {
		return map;
	}

	public MobSpawner getSpawner() {
		return spawner;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	// End of useful code / main
	
	public static void main(String[] args) throws InterruptedException {
		
		final Game game = new Game();
		final Frame f = new Frame(game, MapGenerator.X*Tile.SIZE, MapGenerator.Y*Tile.SIZE);
		
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
	
}
