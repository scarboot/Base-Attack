package base_attack;

public class SniperTower extends Tower {
	
	public static final int PRICE = 45;
	public static final double RANGE = 6.5, COOLDOWN = 3;

	public SniperTower(Game game, Tile tile, TowerMeta<? extends SniperTower> meta) {
		super(game, tile, COOLDOWN, RANGE, PRICE/2, meta);
	}

	@Override
	public boolean shoot() {
		
		final Mob target = findMob();
		
		if(target == null)
			return false;
		
		getGame().getMap().getBullets().add(new SniperBullet(getGame(), getTile(), target));
		
		return true;
		
	}

}
