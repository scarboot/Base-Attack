package base_attack;

import base_attack.ui.Frame;

public class Game implements Updateable {
	
	public static Game game;
	
	private final Map map;
	private final MobSpawner spawner = new MobSpawner(this);
	
	private final TowerMeta<?>[] towerMetas = new TowerMeta<?>[]{
		new TowerMeta<SlingTower>(this, "Sling Tower", 5, SlingBullet.DAMAGE, SlingTower.RANGE, SlingTower.class)
	};
	
	private int money = 1;
	
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

	public void removeMoney(int price) {
		
		if(price > money)
			throw new IllegalArgumentException("price > money: " + price + " > " + money);
		
		money -= price;
	}

	public TowerMeta<?>[] getTowerMetas() {
		return towerMetas;
	}
	
}
