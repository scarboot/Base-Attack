package base_attack;

import java.awt.Color;
import java.util.LinkedList;

public class SniperBullet extends Bullet {
	
	private static final Color c = Color.BLUE;
	public static final double DAMAGE = 3;
	
	private final LinkedList<Mob> mobsTouched = new LinkedList<>();

	public SniperBullet(Game g, double x, double y, PointDouble target) {
		super(g, 2, DAMAGE, c, x, y, target, 8, SniperTower.RANGE);
	}

	public SniperBullet(Game g, Tile tile, Mob target) {
		this(g, tile.x + 0.5, tile.y + 0.5, target.getCenter());
	}
	
	/**
	 * @return Whether to remove the Bullet
	 */
	protected boolean checkColission() {
		
		for(Mob m: getGame().getMap().getMobs()) {
			
			if(mobsTouched.contains(m))
				continue;
			
			if(getHitbox().intersects(m.getHitbox())) {
				
				m.damage(getDamage());
				
				mobsTouched.add(m);
				
				if(mobsTouched.size() >= 2)
					return true;
				
			}
			
		}
		
		return false;
		
	}
	
	@Override
	public double getSpeed() {
		return super.getSpeed() * Math.pow(0.8, mobsTouched.size());
	}

}
