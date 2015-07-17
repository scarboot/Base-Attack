package base_attack;

public class SlingTower extends Tower {
	
	public static final int PRICE = 10;
	public static final double RANGE = 4.5, COOLDOWN = 4;

	public SlingTower(Game game, Tile tile) {
		super(game, tile, COOLDOWN, RANGE, PRICE/2);
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
