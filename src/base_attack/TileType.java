package base_attack;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import base_attack.ui.Images;

public enum TileType {
	
	GRASS(1),
	STONE(1),
	DIRT(0.7); //MAY BE CHANGED
	
	public final double SPEED_FACTOR;
	public final BufferedImage IMAGE;
	
	private TileType(double speedFactor){
		SPEED_FACTOR = speedFactor;
		IMAGE = Images.loadImage(toString());
	}

	public void draw(Graphics2D g, int x, int y) {
		
		final int drawX = x * Tile.SIZE;
		final int drawY = y * Tile.SIZE;
		
		g.drawImage(IMAGE, drawX, drawY, null);
		
	}
	
}