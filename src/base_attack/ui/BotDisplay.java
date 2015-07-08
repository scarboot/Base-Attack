package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;

public class BotDisplay extends Display {
	
	public BotDisplay(Frame f) {
		super(f);
	}
	
	public void draw(Graphics2D g) {
		
		drawBorder(g);
		
		//TRANSLATE UNDER BORDER
		g.translate(0, BORDER);

	}
	
	private void drawBorder(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getFrame().width, BORDER);
		
	}

}
