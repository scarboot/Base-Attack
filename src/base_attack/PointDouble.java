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

}
