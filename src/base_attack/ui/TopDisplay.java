package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TopDisplay extends Display {

	private final BufferedImage
	money = Images.loadImage("Money"),
	clock = Images.loadImage("Clock"),
	fight = Images.loadImage("Fight");
	
	public TopDisplay(Frame f) {
		super(f);
	}
	
	public void draw(Graphics2D g) {
		
		drawBorder(g);
		
		//DRAW MONEY
		drawInCenter(g, money, 0, 0, HEIGHT_INTERNAL, HEIGHT_INTERNAL);
		drawString(g, String.valueOf(getGame().getMoney()), SPACE, MIDDLE);
		
		g.setColor(getGame().getSpawner().isPause() ? Color.BLACK : Color.RED);
		
		//DRAW WAVE
		drawString(g, "Wave " + (getGame().getSpawner().getWave() + 1), SPACE + FONT_WIDTH*8 + GAP , MIDDLE);
		
		g.setColor(Color.BLACK);
		
		//DRAW MOBS
		if(getGame().getSpawner().isPause()) {
			
			drawInCenter(g, clock, getFrame().width - SPACE, 0, HEIGHT_INTERNAL, HEIGHT_INTERNAL);
			drawString(g, getGame().getSpawner().formatTime(), getFrame().width - (SPACE + FONT_WIDTH*4), MIDDLE);
			
		} else {
			
			drawInCenter(g, fight, getFrame().width - SPACE, 0, HEIGHT_INTERNAL, HEIGHT_INTERNAL);
			
		}
		
	}
	
	private void drawBorder(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, HEIGHT_INTERNAL, getFrame().width, BORDER);

	}

}
