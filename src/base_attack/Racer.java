package base_attack;

public class Racer extends Mob {
	
	public static final int VALUE = 2;

	public Racer(Path path) {
		super(path, 0.9, new Hitbox(11), 1.5, VALUE);
	}

}
