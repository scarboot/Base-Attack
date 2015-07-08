package base_attack.ui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import base_attack.Game;

public class Display {
	
	public static final int
	HEIGHT_INTERNAL = 60,
	BORDER = 3,
	HEIGHT = HEIGHT_INTERNAL + BORDER,
	FONT_HEIGHT = HEIGHT_INTERNAL*2/3,
	GAP = HEIGHT_INTERNAL/6,
	SPACE = HEIGHT_INTERNAL + GAP,
	FONT_WIDTH = FONT_HEIGHT*2/3,
	MIDDLE = HEIGHT_INTERNAL/2;
	
	public static final double
	fontHeightActual = FONT_HEIGHT*2/3d;
	
	public static final Font FONT = new Font("Arial", Font.PLAIN, FONT_HEIGHT);
	
	private final Frame frame;
	
	public Display(Frame frame) {
		this.frame = frame;
	}

	public Frame getFrame() {
		return frame;
	}
	
	public Game getGame() {
		return frame.getGame();
	}
	
	public static void drawString(Graphics2D g, String s, int x, int middleY) {
		
		final int y = (int) (middleY + fontHeightActual/2);
		
		g.drawString(s, x, y);
		
	}

	public static void drawInCenter(Graphics2D g, BufferedImage image, int x, int y, int height, int width) {
		
		x += center(width, image.getWidth());
		y += center(height, image.getHeight());
		
		g.drawImage(image, x, y, null);
		
	}

	public static void drawInCenter(Graphics2D g, BufferedImage image, int x, int y) {
		drawInCenter(g, image, x, y, HEIGHT_INTERNAL, HEIGHT_INTERNAL);
	}

	private static int center(int height, int fitIntoHeight) {
		return (height - fitIntoHeight)/2;
	}

}
