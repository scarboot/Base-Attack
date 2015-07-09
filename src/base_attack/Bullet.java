package base_attack;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet {
	
	private final Game game;
	
	private final int radius;
	private final double damage, range;
	private final Color c;

	private final PointDouble pos = new PointDouble(0, 0);
	private final PointDouble vector;
	
	private final Hitbox hitbox;
	
	private double currentDistance;
	
	public Bullet(Game game, int radius, double damage, Color c, double x, double y, PointDouble target, double speed, double range) {
		
		this.game = game;
		
		this.radius = radius;
		this.damage = damage;
		this.c = c;
		
		pos.x = x;
		pos.y = y;
		
		this.hitbox = new Hitbox(x, y, getRadius());
		
		this.vector = PointDouble.getVector(x, y, target.x, target.y).setLength(speed);
		this.range = range;
	}
	
	/**
	 * @return Whether to remove the Bullet
	 */
	public boolean update(double t) {
		
		//UPDATE POSITION
		
		final PointDouble delta = vector.clone().multiply(t);
		
		getPos().add(delta);
		
		//UPDATE HITBOX
		
		getHitbox().setPosition(getPos());
		
		//CHECK DISTANCE
		
		currentDistance += delta.length();
		
		if(currentDistance > range)
			return true;
		
		//CHECK COLISSION
		
		for(Mob m: getGame().getMap().getMobs())
			
			if(getHitbox().intersects(m.getHitbox())) {
				
				m.damage(getDamage());
				
				return true;
				
			}
		
		return false;
		
	}

	private Hitbox getHitbox() {
		return hitbox;
	}

	public int getRadius() {
		return radius;
	}

	public double getDamage() {
		return damage;
	}

	public Color getColor() {
		return c;
	}
	
	public PointDouble getPos() {
		return pos;
	}

	public void draw(Graphics2D g) {
		
		final int x = (int) (getPos().x * Tile.SIZE - getRadius());
		final int y = (int) (getPos().y * Tile.SIZE - getRadius());
		
		g.setColor(getColor());
		
		g.fillOval(x, y, getRadius()*2, getRadius()*2);
		
	}
	
	public Game getGame() {
		return game;
	}

}
