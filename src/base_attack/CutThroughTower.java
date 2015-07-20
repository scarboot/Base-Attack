package base_attack;

public class CutThroughTower extends Tower {
	
	public static final int PRICE = 35;
	public static final double RANGE = 3.5, COOLDOWN = 3;

	public CutThroughTower(Game game, Tile tile, TowerMeta<? extends CutThroughTower> meta) {
		super(game, tile, COOLDOWN, RANGE, PRICE/2, meta);
	}

	@Override
	public boolean shoot() {
		
		final Mob target = findFarthestMob();
		
		if(target == null)
			return false;
		
		getGame().getMap().getBullets().add(new CutThroughBullet(getGame(), getTile(), target));
		
		return true;
		
	}

	private Mob findFarthestMob() {
		
		final double maxDistanceSq = Math.pow(getRange(), 2);
		
		double bestDistanceSq = Double.NEGATIVE_INFINITY;
		Mob bestMob = null;
		
		for(Mob m: getGame().getMap().getMobs()) {
			
			final double distanceSq = m.getLocation().distanceSq(getTile().x, getTile().y);
			
			if(distanceSq > maxDistanceSq)
				continue;
			
			if(distanceSq > bestDistanceSq) {
				bestDistanceSq = distanceSq;
				bestMob = m;
			}
			
		}
		
		return bestMob;
		
	}

}
