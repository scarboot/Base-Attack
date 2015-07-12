package base_attack;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import base_attack.ui.Images;

public abstract class Mob implements Updateable {

	private final BufferedImage image;
	private final Movement movement;
	private final Hitbox hitbox;
	private final int money;
	
	private double health;
	
	public Mob(Path path, double secondsPerTile, Hitbox hitbox, double health, int money) {
		
		this.image = Images.loadImage(getClass().getSimpleName());
		this.movement = new Movement(path, secondsPerTile);
		
		this.hitbox = hitbox;
		hitbox.setPosition(this);
		
		this.money = money;
		
		this.health = health;
		
	}
	
	@Override
	public void update(double t) {
		getMovement().update(t);
		hitbox.setPosition(this);
	}
	
	public void onRemove(Game g) {
		
		if(isDead())
			
			g.addMoney(getMoney());
		
		else if(isPathFinished())
			
			g.getBase().hit();
		
	}

	public Movement getMovement() {
		return movement;
	}

	public Image getImage() {
		return image;
	}

	public boolean shouldBeRemoved() {
		return isPathFinished() || isDead();
	}
	
	public boolean isDead() {
		return health <= 0;
	}
	
	public boolean isPathFinished() {
		return getMovement().isFinished();
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

	public void draw(Graphics2D g) {
		
		final PointDouble p = getMovement().getExactLocation();
		
		final int
		x = (int) (p.x * Tile.SIZE),
		y = (int) (p.y*Tile.SIZE);
		
		g.drawImage(getImage(), x, y, null);
		
	}
	
	public int getMoney() {
		return money;
	}
	
}
