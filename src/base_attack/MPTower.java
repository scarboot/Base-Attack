package base_attack;

public class MPTower extends Tower {
	
	public static final double RANGE = 2.5;

	public MPTower(Game game, Tile tile) {
		super(game, tile, 0.5, RANGE);
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
