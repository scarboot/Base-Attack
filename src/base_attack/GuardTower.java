package base_attack;

public class GuardTower extends Tower {
	
	public static final int PRICE = 25;
	public static final double RANGE = 5.5, COOLDOWN = 2;

	public GuardTower(Game game, Tile tile, TowerMeta<? extends GuardTower> meta) {
		super(game, tile, COOLDOWN, RANGE, PRICE/2, meta);
	}

	@Override
	public boolean shoot() {
		
		final Mob target = findMob();
		
		if(target == null)
			return false;
		
		getGame().getMap().getBullets().add(new SlingBullet(getGame(), getTile(), target, RANGE));
		
		return true;
		
	}

}
