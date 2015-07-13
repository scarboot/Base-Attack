package base_attack;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;

import base_attack.ui.Images;

public class TowerMeta<T extends Tower> {

	private final Game game;
	private final BufferedImage imageColored, imageGrey;
	private final String name;
	private final int price, damage;
	private final double range;
	private final Class<T> clazz;
	
	public TowerMeta(Game game, String name, int price, int damage, double range, Class<T> clazz) {
		this.game = game;
		this.imageColored = Images.loadImage(clazz.getSimpleName());
		this.imageGrey = Images.loadImage(clazz.getSimpleName() + "Grey");
		this.name = name;
		this.price = price;
		this.damage = damage;
		this.range = range;
		this.clazz = clazz;
	}

	public void placeChecked(int x, int y) {
		
		if(!canPlace(x, y))
			throw new IllegalArgumentException();
		
		buyUnchecked();
		
		placeUnchecked(getGame().getMap().getTiles()[x][y]);
		
	}
	
	private void buyUnchecked() {
		
		getGame().removeMoney(getPrice());
		
	}

	public boolean isAllowed(int x, int y) {
		return canBuy() && canPlace(x, y);
	}
	
	public boolean canPlace(int x, int y) {
		return getGame().getMap().getTiles()[x][y].canBuildTower();
	}

	public boolean canBuy() {
		return getGame().getMoney() >= getPrice();
	}
	
	protected void placeUnchecked(Tile tile) {
		
		final T tower = generateTower(game, tile);
		
		tile.setTower(tower);
		
	}

	protected T generateTower(Game game, Tile tile) {
		
		try {
			
			Constructor<T> constructor = clazz.getConstructor(Game.class, Tile.class);
			return constructor.newInstance(game, tile);
			
		} catch (Exception e) {
			throw new RuntimeException("Error while contructing a " + clazz.getSimpleName() + " with reflection", e);
		}
		
	}

	public int getPrice() {
		return price;
	}
	
	public BufferedImage getImageColored() {
		return imageColored;
	}
	
	public BufferedImage getImageGrey() {
		return imageGrey;
	}
	
	public int getDamage() {
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

	public BufferedImage getImage() {
		return canBuy() ? getImageColored() : getImageGrey();
	}

	public Color getColor() {
		return canBuy() ? Color.GREEN.darker() : Color.RED.darker();
	}

}
