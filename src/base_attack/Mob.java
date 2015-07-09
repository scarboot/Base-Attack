package base_attack;

import java.awt.Image;
import java.awt.image.BufferedImage;

import base_attack.ui.Images;

public abstract class Mob implements Updateable {

	private final BufferedImage image;
	private final Movement movement;
	
	public Mob(Path path, double secondsPerTile) {
		this.image = Images.loadImage(getClass().getSimpleName());
		this.movement = new Movement(path, secondsPerTile);
	}
	
	@Override
	public void update(double t) {
		getMovement().update(t);
	}

	public Movement getMovement() {
		return movement;
	}

	public Image getImage() {
		return image;
	}

	public boolean isDead() {
		return getMovement().isFinished(); //TODO || health == 0;
	}

	public PointDouble getLocation() {
		return getMovement().getExactLocation();
	}
	
}
