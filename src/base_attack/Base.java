package base_attack;

/**
 * Created by Yannick on 07.07.2015.
 * Project: Base-Attack
 */
public class Base extends Tower {

	public Base(Game game, Tile tile) {
		super(game, tile, Double.POSITIVE_INFINITY, 0);
	}

	public Base() {
		this(null, null);
	}

	@Override
	public void update(double t) {
	}
	
	@Override
	public boolean shoot() {
		throw new UnsupportedOperationException();
	}
	
}
