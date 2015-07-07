package base_attack;

public class Movement implements Updateable {
	
	private final Path path;
	private final double seconds;
	
	private int index;
	private double progress;
	
	public Movement(Path path, double seconds) {
		this.path = path;
		this.seconds = seconds;
	}

	@Override
	public void update(double t) {
		
		progress += getLocalSpeedFactor() * t;
		
		while(progress >= seconds) {
			
			progress -= seconds;
			index++;
			
		}
		
	}

	private double getLocalSpeedFactor() {
		return getTile().getType().WALK_SPEED_FACTOR;
	}
	
	public PointDouble getExactLocation() {
		
		double
		x1 = getTile().x,
		y1 = getTile().y,
		x2 = getNextTile().x,
		y2 = getNextTile().y,
		deltaX = x2 - x1,
		deltaY = y2 - y1;
		
		return new PointDouble(x1 + deltaX*getPercentage(), y1 + deltaY*getPercentage());
		
	}

	private Tile getTile() {
		return path.getTile(index);
	}
	
	private Tile getNextTile() {
		return path.getTile(index + 1);
	}
	
	private double getPercentage() {
		return progress/seconds;
	}

}
