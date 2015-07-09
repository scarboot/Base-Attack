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
		
		if(isFinished())
			return;
		
		progress += getLocalSpeedFactor() * t;
		
		while(progress >= seconds) {
			
			progress -= seconds;
			index++;
			
		}
		
		if(isFinished())
			progress = 0;
		
	}

	private double getLocalSpeedFactor() {
		
		final Tile t;
		
		if(isLastTile())
			t = getTile();
		else
			t = getPercentage() < 0.5 ? getTile() : getNextTile();
		
		return t.getType().SPEED_FACTOR;
		
	}
	
	public PointDouble getExactLocation() {
		
		if(isFinished() || isLastTile())
			return new PointDouble(getTile().x, getTile().y);
		
		double
		x1 = getTile().x,
		y1 = getTile().y,
		x2 = getNextTile().x,
		y2 = getNextTile().y,
		deltaX = x2 - x1,
		deltaY = y2 - y1;
		
		return new PointDouble(x1 + deltaX*getPercentage(), y1 + deltaY*getPercentage());
		
	}

	private boolean isLastTile() {
		return index == path.getLength() - 1;
	}

	private Tile getTile() {
		if(path.getTile(index).x >= 0 && path.getTile(index).x < 15 && path.getTile(index).y >= 0 && path.getTile(index).y < 9)
		return Game.game.getMap().getTiles()[path.getTile(index).x][path.getTile(index).y];
		return path.getTile(index);
	}
	
	private Tile getNextTile() {
		int index = this.index + 1;
		if(path.getTile(index).x >= 0 && path.getTile(index).x < 15 && path.getTile(index).y >= 0 && path.getTile(index).y < 9)
		return Game.game.getMap().getTiles()[path.getTile(index).x][path.getTile(index).y];
		return path.getTile(index);
	}
	
	private double getPercentage() {
		return progress/seconds;
	}

	public boolean isFinished() {
		return index == path.getLength() - 1;
	}

}
