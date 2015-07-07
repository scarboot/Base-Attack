package base_attack;

import java.awt.*;
import java.awt.image.BufferedImage;

import base_attack.ui.Images;

public abstract class Tower implements Updateable {
	
	private final BufferedImage image;

	int width, height;
	Point position; 
	
	public Tower() {
		image = Images.loadImage(getClass().getSimpleName());
	}
	
	public BufferedImage getImage() {
		return image;
	}
}
