package base_attack;

public class SlingTower extends Tower {

	public SlingTower(Game game, Tile tile) {
		super(game, tile, 1, 4);
	}

	@Override
	public boolean shoot() {
		
		final Mob target = findMob();
		
		if(target == null)
			return false;
		
		getGame().getMap().getBullets().add(null); //TODO add bullet
		
		
		
		return true;
		
	}

}
