package base_attack;

import java.awt.Color;

public class MPBullet extends Bullet {
	
	private static final Color c = Color.RED;
	public static final double DAMAGE = 0.5;

	public MPBullet(Game g, double x, double y, PointDouble target) {
		super(g, 2, DAMAGE, c, x, y, target, 6, SlingTower.RANGE);
	}

	public MPBullet(Game g, Tile tile, Mob target) {
		this(g, tile.x + 0.5, tile.y + 0.5, target.getCenter());
	}
}
