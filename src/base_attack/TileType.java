package base_attack;

public enum TileType {
	
	GRASS(1),
	STONE(Double.NaN),
	DIRT(0.75); //MAY BE CHANGED
	
	public final double WALK_SPEED_FACTOR;
	
	private TileType(double walkSpeedFactor) {
		WALK_SPEED_FACTOR = walkSpeedFactor;
	}
	
}