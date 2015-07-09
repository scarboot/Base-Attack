package base_attack;

import java.awt.Color;

public class SlingBullet extends Bullet {
	
	private static final Color c = Color.RED;

	public SlingBullet(Game g, double x, double y, PointDouble target, double range) {
		super(g, 2, 1, c, x, y, target, 3, range);
	}

	public SlingBullet(Game g, Tile tile, Mob target, double radius) {
		this(g, tile.x + 0.5, tile.y + 0.5, target.getCenter(), radius);
	}

}
