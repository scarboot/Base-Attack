package base_attack;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;

import base_attack.ui.Images;

public class TowerMeta<T extends Tower> {

	private final Game game;
	private final BufferedImage imageColored, imageGray;
	private final String name;
	private final int price;
	private final double damage, cooldown, range;
	private final Class<T> clazz;
	
	public TowerMeta(Game game, String name, int price, double damage, double cooldown, double range, Class<T> clazz) {
		this.game = game;
		this.imageColored = Images.loadImage(clazz.getSimpleName());
		this.imageGray = Images.loadImage(clazz.getSimpleName() + "Gray");
		this.name = name;
		this.price = price;
		this.damage = damage;
		this.cooldown = cooldown;
		this.range = range;
		this.clazz = clazz;
	}
	
	public TowerMeta(Game game, String name, int price, Class<T> clazz) {
		this(game, name, price, getDamage(clazz), getCooldown(clazz), getRange(clazz), clazz);
	}
	
	public void placeUnchecked(Tile tile) {
		
		final T tower = generateTower(game, tile);
		
		tile.setTower(tower);
		
	}

	protected T generateTower(Game game, Tile tile) {
		
		try {
			
			Constructor<T> constructor = clazz.getConstructor(Game.class, Tile.class, TowerMeta.class);
			return constructor.newInstance(game, tile, this);
			
		} catch (Exception e) {
			throw new RuntimeException("Error while contructing a " + clazz.getSimpleName() + " with reflection", e);
		}
		
	}

	public int getPriceSimpel() {
		return price;
	}
	
	public BufferedImage getImageColored() {
		return imageColored;
	}
	
	public BufferedImage getImageGray() {
		return imageGray;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public String getName() {
		return name;
	}
	
	public double getRange() {
		return range;
	}

	public Game getGame() {
		return game;
	}

	public BufferedImage getImage(boolean canBuy) {
		return canBuy ? getImageColored() : getImageGray();
	}

	public double getCooldown() {
		return cooldown;
	}

	private static double getRange(Class<?> c) {
		try {
			return (double) c.getDeclaredField("RANGE").get(null);
		} catch (Exception e) {
			throw new RuntimeException("Exception while getting 'RANGE' of " + c.getSimpleName(), e);
		}
	}

	private static double getCooldown(Class<?> c) {
		try {
			return (double) c.getDeclaredField("COOLDOWN").get(null);
		} catch (Exception e) {
			throw new RuntimeException("Exception while getting 'COOLDOWN' of " + c.getSimpleName(), e);
		}
	}

	private static double getDamage(Class<?> c) {
		try {
			return Double.parseDouble(String.valueOf(getBulletClass(c).getDeclaredField("DAMAGE").get(null)));
		} catch (Exception e) {
			throw new RuntimeException("Exception while getting 'DAMAGE' of " + c.getSimpleName(), e);
		}
	}

	private static Class<?> getBulletClass(Class<?> c) throws ClassNotFoundException {
		return Class.forName(c.getName().replace("Tower", "Bullet"));
	}

	public boolean canBuySimpel() {
		return getGame().getMoney() >= getPriceSimpel();
	}

}
