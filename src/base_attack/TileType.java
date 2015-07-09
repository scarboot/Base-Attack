package base_attack;

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
	
}