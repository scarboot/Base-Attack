package base_attack;

import base_attack.ui.Frame;
import base_attack.ui.Mouse;

public class Game {
	
	public static Game game;
	
	private final Map map;
	private final MobSpawner spawner = new MobSpawner(this);
	
	private final TowerMeta<?>[] towerMetas = new TowerMeta<?>[]{
			new TowerMeta<SlingTower>(this, "Sling Tower", SlingTower.PRICE, SlingTower.class),
			new TowerMeta<MPTower>(this, "MP Tower", MPTower.PRICE, MPTower.class),
			new TowerMeta<CutThroughTower>(this, "Cut-Through Tower", CutThroughTower.PRICE, CutThroughTower.class),
	};
	
	private Frame frame;
	private Base base;
	private int money = 30;
	
	public Game() {
		
		Map m = null;
		
		while(m == null) {
			try {
				m = MapGenerator.generateMap(this);
			} catch (Exception e) {
				e.printStackTrace();
				base = null;
			}
		}
		
		this.map = m;
		
		/*
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
		*/
		
	}
	
	public void update(Frame f, double t) {
		
		if(!isGameOver()) {
			
			getMap().update(t);
			getSpawner().update(t);
			f.update(t);
			
		}
		
		Mouse.INSTANCE.update(t);
		//TODO KEYBOARD
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		game = new Game();
		final Frame f = new Frame(game, MapGenerator.X*Tile.SIZE, MapGenerator.Y*Tile.SIZE);
		
		long lastFrame = System.currentTimeMillis();
		
		while(true) {
			
			final long thisFrame = System.currentTimeMillis();
			final double delta = (thisFrame - lastFrame)*0.001; // seconds
			lastFrame = thisFrame;
			
			game.update(f, delta);
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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void removeMoney(int price) {
		
		if(price > money)
			throw new IllegalArgumentException("price > money: " + price + " > " + money);
		
		money -= price;
	}

	public TowerMeta<?>[] getTowerMetas() {
		return towerMetas;
	}

	public void addMoney(int i) {
		money += i;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		
		if(this.base != null)
			throw new IllegalStateException();
		
		this.base = base;
		
	}
	
	public boolean isGameOver() {
		return getBase().isDead();
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		
		if(getFrame() != null)
			throw new IllegalStateException();
		
		this.frame = frame;
	}
	
}
