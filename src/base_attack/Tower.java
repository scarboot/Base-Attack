package base_attack;

import java.awt.image.BufferedImage;

import base_attack.ui.Images;

public abstract class Tower implements Updateable {
	
	private final BufferedImage image;
	
	private final Game game;
	private final Tile tile;	
	private final Cooldown cooldown;
	private final double radius;
	
	public Tower(Game game, Tile tile, double cooldown, double radius) {
		
		this.game = game;
		this.tile = tile;
		this.cooldown = new Cooldown(cooldown);
		this.radius = radius;
		
		image = Images.loadImage(getClass().getSimpleName());
	}
	
	@Override
	public void update(double t) {
		
		getCooldown().update(t);
		
		if(getCooldown().ready()) {
			
			if(shoot())
				getCooldown().use();
			
		}
		
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public abstract boolean shoot();

	public double getRadius() {
		return radius;
	}

	private Cooldown getCooldown() {
		return cooldown;
	}
	
	public Game getGame() {
		return game;
	}
	
	public Mob findMob() {
		
		double bestDistanceSq = Double.NEGATIVE_INFINITY;
		Mob bestMob = null;
		
		for(Mob m: getGame().getMap().getMobs()) {
			
			final double distanceSq = m.getLocation().distanceSq(getTile().x, getTile().y);
			
			if(distanceSq > bestDistanceSq) {
				bestDistanceSq = distanceSq;
				bestMob = m;
			}
			
		}
		
		return bestMob;
		
	}
	
}
