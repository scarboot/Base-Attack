package base_attack;

import base_attack.ui.Frame;

public class Game implements Updateable {
	
	private int money = 0;
	private final Map map;
	private final MobSpawner spawner = new MobSpawner(this);
	public static Game game;
	
	public Game() {
		
		Map m = null;
		
		while(m == null) {
			try {
				m = MapGenerator.generateMap(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.map = m;
		
		for(int i = 0; i < 3; i++)
			while(true) {
				
				int x = (int) (Math.random()*MapGenerator.X);
				int y = (int) (Math.random()*MapGenerator.Y);
				
				final Tile t = getMap().getTiles()[x][y];
				
				if(!t.canBuildTower())
					continue;
				
				boolean nearEnough = false;
				
				LOOP: for(Tile[] tiles: getMap().getTiles())
					for(Tile tile: tiles){
						
						if(tile.getType() == TileType.STONE)
							continue;
						
						if(PointDouble.distanceSq(x, y, tile.x, tile.y) < 3) {
							nearEnough = true;
							break LOOP;
						}
						
					}
				
				if(!nearEnough)
					continue;
				
				t.setTower(new SlingTower(this, t));
				
				break;
				
			}
		
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
		
		game = new Game();
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
