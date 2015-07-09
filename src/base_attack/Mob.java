package base_attack;

import java.awt.Image;
import java.awt.image.BufferedImage;

import base_attack.ui.Images;

public abstract class Mob implements Updateable {

	private final BufferedImage image;
	private final Movement movement;
	private final Hitbox hitbox;
	
	private double health;
	
	public Mob(Path path, double secondsPerTile, Hitbox hitbox, double health) {
		
		this.image = Images.loadImage(getClass().getSimpleName());
		this.movement = new Movement(path, secondsPerTile);
		
		this.hitbox = hitbox;
		hitbox.setPosition(this);
		
		this.health = health;
		
	}
	
	@Override
	public void update(double t) {
		getMovement().update(t);
		hitbox.setPosition(this);
	}

	public Movement getMovement() {
		return movement;
	}

	public Image getImage() {
		return image;
	}

	public boolean isDead() {
		return getMovement().isFinished() || health <= 0;
	}

	public PointDouble getLocation() {
		return getMovement().getExactLocation();
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public PointDouble getCenter() {
		return getLocation().add(0.5, 0.5);
	}

	public void damage(double damage) {
		health -= damage;
	}
	
}
