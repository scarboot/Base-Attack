package base_attack;

/**
 * 
 * A 'Tank' reduces the damage from each hit by a constant amount (capped at a certain percentage)
 *
 */
public class Tank extends Mob {

	public static final int VALUE = 3;
	
	public static final double REDUCTION = 0.25;
	
	public Tank(Path path) {
		super(path, 2, new Hitbox(20), 7, VALUE);
	}
	
	@Override
	public void damage(double damage) {
		
		if(damage == 0)
			return;
		
		if(damage < 0)
			throw new IllegalArgumentException();
		
		final double maxReduction = damage * 0.8;
		
		final double reduction = REDUCTION > maxReduction ? maxReduction : REDUCTION;
		
		super.damage(damage - reduction);
		
	}

}
