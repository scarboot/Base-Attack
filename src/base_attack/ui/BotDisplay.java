package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import base_attack.TowerMeta;

public class BotDisplay extends Display {
	
	public static final int BUTTON_CONTAINER_SIZE = 60;
	
	private final TowerDisplay towerDisplay = new TowerDisplay();
	
	private final ContentContainer towerDisplayContainer;
	private final ContentContainer[] towerSelection;
	
	public BotDisplay(Frame f) {
		
		super(f, TowerDisplay.FONT_HEIGHT + TowerDisplay.LINE_HEIGHT + GAP * (1 + 2));
		
		//TOWER DISPLAY
		
		final int width = f.width;
		final int towerDisplayWidth = TowerDisplay.WIDTH_SPACE*3;
		towerDisplayContainer = new ContentContainer(width - towerDisplayWidth, 0, towerDisplayWidth, heightInternal, towerDisplay, GAP);
		
		towerDisplay.setMeta(f.getGame().getTowerMetas()[0]);
		
		//TOWER SELECTION
		
		final TowerMeta<?>[] towerMetas = getGame().getTowerMetas();
		towerSelection = new ContentContainer[towerMetas.length];
		
		for(int i = 0; i < towerMetas.length; i++) {
			
			final TowerButton button = new TowerButton(towerMetas[i]);
			
			final ContentContainer container = new ContentContainer(0, 0, BUTTON_CONTAINER_SIZE, BUTTON_CONTAINER_SIZE, button, GAP/2-2);
			System.out.println(button.width);
			towerSelection[i] = container;
			
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		drawBorder(g);
		
		for(ContentContainer c: towerSelection)
			c.draw(g);
		
		towerDisplayContainer.draw(g);

	}
	
	private void drawBorder(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getFrame().width, BORDER);
		
		//TRANSLATE UNDER BORDER
		g.translate(0, BORDER);
		
		g.fillRect(towerDisplayContainer.x, 0, Display.BORDER, height());
		
	}

}
