package base_attack;

public class MPTower extends Tower {
	
	public static final int PRICE = 35;
	public static final double RANGE = 2.5, COOLDOWN = 0.5;

	public MPTower(Game game, Tile tile, TowerMeta<? extends MPTower> meta) {
		super(game, tile, COOLDOWN, RANGE, PRICE/2, meta);
	}

	@Override
	public boolean shoot() {
		
		final Mob target = findMob();
		
		if(target == null)
			return false;
		
		getGame().getMap().getBullets().add(new MPBullet(getGame(), getTile(), target));
		
		return true;
		
	}
}
