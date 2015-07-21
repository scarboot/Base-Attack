package base_attack;

import java.awt.Color;

public class LMGBullet extends Bullet {
	
	private static final Color c = Color.RED.darker();
	public static final double DAMAGE = 0.4;

	public LMGBullet(Game g, double x, double y, PointDouble target) {
		super(g, 2, DAMAGE, c, x, y, target, 5.5, LMGTower.RANGE);
	}

	public LMGBullet(Game g, Tile tile, Mob target) {
		this(g, tile.x + 0.5, tile.y + 0.5, target.getCenter());
	}
}
