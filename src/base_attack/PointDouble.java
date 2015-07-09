package base_attack;

public class PointDouble {
	
	public double x, y;
	
	public PointDouble(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distanceSq(double x, double y) {
		final double dx = x - this.x;
		final double dy = y - this.y;
		return Math.pow(dx, 2) + Math.pow(dy, 2);
	}
	
	public double distance(double x, double y) {
		return Math.sqrt(distanceSq(x, y));
	}

	public static PointDouble getVector(double x1, double y1, double x2, double y2) {
		return new PointDouble(x2 - x1, y2 - y1);
	}

	public PointDouble setLength(double speed) {
		normalize();
		multiply(speed);
		return this;
	}

	private PointDouble normalize() {
		divide(length());
		return this;
	}

	private PointDouble divide(double d) {
		multiply(1/d);
		return this;
	}

	public PointDouble multiply(double d) {
		x *= d;
		y *= d;
		return this;
	}

	public double length() {
		return distance(0, 0);
	}

	public PointDouble add(PointDouble p) {
		add(p.x, p.y);
		return this;
	}

	public PointDouble add(PointDouble p, double factor) {
		add(p.x*factor, p.y*factor);
		return this;
	}

	public PointDouble add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	@Override
	public PointDouble clone() {
		return new PointDouble(x, y);
	}

	public double distanceSq(PointDouble p) {
		return distanceSq(p.x, p.y);
	}

	public PointDouble set(PointDouble p) {
		x = p.x;
		y = p.y;
		return this;
	}

	public double distance(PointDouble p) {
		return distance(p.x, p.y);
	}
	
	@Override
	public String toString() {
		return x + " / " + y;
	}

}
