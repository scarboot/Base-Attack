package base_attack;

import java.awt.Color;

public class SlingBullet extends Bullet {
	
	private static final Color c = Color.DARK_GRAY;
	public static final int DAMAGE = 1;

	public SlingBullet(Game g, double x, double y, PointDouble target, double range) {
		super(g, 4, DAMAGE, c, x, y, target, 3, range);
	}

	public SlingBullet(Game g, Tile tile, Mob target, double range) {
		this(g, tile.x + 0.5, tile.y + 0.5, target.getCenter(), range);
	}

}
