package base_attack;

import java.awt.Color;

public class SlingBullet extends Bullet {
	
	private static final Color c = Color.DARK_GRAY;

	public SlingBullet(Game g, double x, double y, PointDouble target) {
		super(g, 4, 1, c, x, y, target, 3, SlingTower.RANGE);
	}

	public SlingBullet(Game g, Tile tile, Mob target) {
		this(g, tile.x + 0.5, tile.y + 0.5, target.getCenter());
	}

}
