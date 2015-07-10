package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import base_attack.TowerMeta;

public class BotDisplay extends Display {
	
	public static final int BUTTON_CONTAINER_SIZE = 60, TOWER_BUTTON_FREE_GAP = 3;
	
	private final TowerDisplay towerDisplay = new TowerDisplay();
	
	private final ContentContainer<TowerDisplay> towerDisplayContainer;
	private final ContentContainer<TowerButton>[] towerSelection;
	
	@SuppressWarnings("unchecked")
	public BotDisplay(Frame f) {
		
		super(f, TowerDisplay.FONT_HEIGHT + TowerDisplay.LINE_HEIGHT + GAP * (1 + 2));
		
		//TOWER DISPLAY
		
		final int width = f.width;
		final int towerDisplayWidth = TowerDisplay.WIDTH_SPACE*3;
		towerDisplayContainer = new ContentContainer<TowerDisplay>(width - towerDisplayWidth, 0, towerDisplayWidth, heightInternal, towerDisplay, GAP);
		
		towerDisplay.setMeta(f.getGame().getTowerMetas()[0]);
		
		//TOWER SELECTION
		
		final TowerMeta<?>[] towerMetas = getGame().getTowerMetas();
		towerSelection = new ContentContainer[towerMetas.length];
		
		final int beginX = GAP, beginY = height()/2 - BUTTON_CONTAINER_SIZE/2;
		
		for(int i = 0; i < towerMetas.length; i++) {
			
			final TowerButton button = new TowerButton(towerMetas[i], getFrame());
			
			final ContentContainer<TowerButton> container = new ContentContainer<TowerButton>(beginX + i*(BUTTON_CONTAINER_SIZE + GAP), beginY, BUTTON_CONTAINER_SIZE, BUTTON_CONTAINER_SIZE, button, GAP/2 - TOWER_BUTTON_FREE_GAP);
			towerSelection[i] = container;
			
		}
		
	}
	
	@Override
	public void update(double t) {
		
		towerDisplay.update(t);
		
		for(ContentContainer<TowerButton> c: towerSelection)
			c.getContent().update(t);
		
	}
	
	public void draw(Graphics2D g) {
		
		drawBorder(g);
		
		for(ContentContainer<TowerButton> c: towerSelection)
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
	
	public TowerDisplay getTowerDisplay() {
		return towerDisplay;
	}

}
