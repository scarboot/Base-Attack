package base_attack;

public class Hitbox {
	
	private final PointDouble pos = new PointDouble(0, 0);
	private final double radius;
	
	public Hitbox(int radius) {
		this(0, 0, radius);
	}

	public Hitbox(double x, double y, double radius) {
		pos.x = x;
		pos.y = y;
		this.radius = radius/Tile.SIZE;
	}

	public boolean intersects(Hitbox h) {
		
		return getPos().distanceSq(h.getPos()) < Math.pow(getRadius() + h.getRadius(), 2);
	}

	public void setPosition(Mob mob) {
		getPos().set(mob.getCenter());
	}
	
	public PointDouble getPos() {
		return pos;
	}
	public double getRadius() {
		return radius;
	}

	public void setPosition(PointDouble p) {
		getPos().set(p);
	}

}
