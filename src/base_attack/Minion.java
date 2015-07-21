package base_attack;

public class Minion extends Mob {
	
	public static final int VALUE = 1;

	public Minion(Path path) {
		super(path, 1.7, new Hitbox(17), 3, VALUE);
	}

}
