package base_attack;

public class SlingTower extends Tower {
	
	public static final double RANGE = 4.5, COOLDOWN = 5;

	public SlingTower(Game game, Tile tile) {
		super(game, tile, COOLDOWN, RANGE);
	}

	@Override
	public boolean shoot() {
		
		final Mob target = findMob();
		
		if(target == null)
			return false;
		
		getGame().getMap().getBullets().add(new SlingBullet(getGame(), getTile(), target));
		
		return true;
		
	}

}
