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
		super(f, 60);
	}
	
	public void draw(Graphics2D g) {
		
		drawBorder(g);
		
		//DRAW MONEY
		drawInCenter(g, money, 0, 0, height(), height());
		drawString(g, String.valueOf(getGame().getMoney()), space(), middle());
		
		g.setColor(getGame().getSpawner().isPause() ? Color.BLACK : Color.RED);
		
		//DRAW WAVE
		drawString(g, "Wave " + (getGame().getSpawner().getWave() + 1), space() + FONT_WIDTH*8 + GAP , middle());
		
		g.setColor(Color.BLACK);
		
		//DRAW MOBS
		if(getGame().getSpawner().isPause()) {
			
			drawInCenter(g, clock, getFrame().width - space(), 0, height(), height());
			drawString(g, getGame().getSpawner().formatTime(), getFrame().width - (space() + FONT_WIDTH*4), middle());
			
		} else {
			
			drawInCenter(g, fight, getFrame().width - space(), 0, height(), height());
			
		}
		
	}
	
	private void drawBorder(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, height(), getFrame().width, BORDER);

	}

}
