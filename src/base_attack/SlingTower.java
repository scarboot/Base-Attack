package base_attack;

public class SlingTower extends Tower {

	public SlingTower(Game game, Tile tile) {
		super(game, tile, 2, 4);
	}

	@Override
	public boolean shoot() {
		
		final Mob target = findMob();
		
		if(target == null)
			return false;
		
		getGame().getMap().getBullets().add(new SlingBullet(getGame(), getTile(), target, getRadius()));
		
		return true;
		
	}

}
