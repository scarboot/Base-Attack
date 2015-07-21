package base_attack;

import java.awt.Color;
import java.util.LinkedList;

public class CutThroughBullet extends Bullet {
	
	private static final Color c = Color.BLACK;
	public static final double DAMAGE = 1;
	
	private final LinkedList<Mob> mobsTouched = new LinkedList<>();

	public CutThroughBullet(Game g, double x, double y, PointDouble target) {
		super(g, 3, DAMAGE, c, x, y, target, 4.5, CutThroughTower.RANGE);
	}

	public CutThroughBullet(Game g, Tile tile, Mob target) {
		this(g, tile.x + 0.5, tile.y + 0.5, target.getCenter());
	}
	
	protected boolean checkColission() {
		
		for(Mob m: getGame().getMap().getMobs()) {
			
			if(mobsTouched.contains(m))
				continue;
			
			if(getHitbox().intersects(m.getHitbox())) {
				
				m.damage(getDamage());
				
				mobsTouched.add(m);
				
			}
			
		}
		
		return false;
		
	}

}
