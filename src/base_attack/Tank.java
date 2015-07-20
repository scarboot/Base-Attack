package base_attack;

/**
 * 
 * A 'Tank' reduces the damage from each hit by a constant amount (max. 50% damage reduction)
 *
 */
public class Tank extends Mob {

	public static final int VALUE = 3;
	
	public static final double REDUCTION = 0.1;
	
	public Tank(Path path) {
		super(path, 2, new Hitbox(20), 9, VALUE);
	}
	
	@Override
	public void damage(double damage) {
		
		if(damage == 0)
			return;
		
		if(damage < 0)
			throw new IllegalArgumentException();
		
		final double maxReduction = damage * 0.5;
		
		final double reduction = REDUCTION > maxReduction ? maxReduction : REDUCTION;
		
		super.damage(damage - reduction);
		
	}

}
