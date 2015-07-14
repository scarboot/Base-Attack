package base_attack;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import base_attack.ui.Images;

public abstract class Mob implements Updateable {
	
	private final BufferedImage[] images;
	
	private final Movement movement;
	private final Hitbox hitbox;
	private final int money;
	private final double maxHealth;
	
	private double health;
	
	public Mob(Path path, double secondsPerTile, Hitbox hitbox, double health, int money) {
		
		this.images = loadImages();
		
		this.movement = new Movement(path, secondsPerTile);
		
		this.hitbox = hitbox;
		hitbox.setPosition(this);
		
		this.money = money;
		
		this.maxHealth = health;
		this.health = health;
		
	}
	
	private BufferedImage[] loadImages() {
		
		if(getImages() != null)
			throw new IllegalStateException();
		
		final ArrayList<BufferedImage> imagesList = new ArrayList<>();
		
		for(int i = 0; true; i++) {
			
			try {
				
				final BufferedImage image = Images.loadImage(getClass().getSimpleName() + (i == 0 ? "" : (i+1)));
				imagesList.add(image);
				
			} catch (RuntimeException e) {
				break;
			}
			
		}
		
		if(imagesList.isEmpty())
			throw new RuntimeException("Image(s) not found: " + getClass().getSimpleName() + ".png");
		
		return imagesList.toArray(new BufferedImage[imagesList.size()]);
		
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
		
		double missingPercentage = 1 - (getHealthClean()/getMaxHealth());
		
		final double percentagePerImage = 1d / getImages().length;
		
		int index = ((int) (missingPercentage/percentagePerImage));
		
		if(index >= getImages().length)
			index = getImages().length - 1;
		
		return getImages()[index];
		
	}

	private double getHealthClean() {
		return getHealth() < 0 ? 0 : getHealth();
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

	public double getMaxHealth() {
		return maxHealth;
	}
	
	public double getHealth() {
		return health;
	}
	
	public BufferedImage[] getImages() {
		return images;
	}
	
}
