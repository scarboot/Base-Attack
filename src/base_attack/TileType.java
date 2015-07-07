package base_attack;

import java.awt.image.BufferedImage;

import base_attack.ui.Images;

public enum TileType {
	
	GRASS(1),
	STONE(1),
	DIRT(0.75); //MAY BE CHANGED
	
	public final double WALK_SPEED_FACTOR;
	public final BufferedImage IMAGE;
	
	private TileType(double walkSpeedFactor){
		WALK_SPEED_FACTOR = walkSpeedFactor;
		IMAGE = Images.loadImage(toString());
	}
	
}