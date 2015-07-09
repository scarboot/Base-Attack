package base_attack;

import java.awt.Color;

public class Bullet implements Updateable {
	
	private final int radius;
	private final double damage;
	private final Color c;

	private final PointDouble pos = new PointDouble(0, 0);
	private final PointDouble vector;
	
	public Bullet(int radius, double damage, Color c, double x, double y, PointDouble target) {
		this.radius = radius;
		this.damage = damage;
		this.c = c;
		pos.x = x;
		pos.y = y;
		this.vector = target;
	}

	@Override
	public void update(double t) {
		
	}

	public int getRadius() {
		return radius;
	}

	public double getDamage() {
		return damage;
	}

	public Color getColor() {
		return c;
	}
	
	public PointDouble getPos() {
		return pos;
	}

}
