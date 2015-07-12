package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import base_attack.TowerMeta;

public class BotDisplay extends Display {
	
	public static final int BUTTON_CONTAINER_SIZE = 60, TOWER_BUTTON_FREE_GAP = 3;
	
	private final DisplaySpot displaySpot = new DisplaySpot();
	private final TowerMetaDisplay towerMetaDisplay;
	
	private final ContentContainer<Container> displaySpotContainer;
	private final ContentContainer<TowerButton>[] towerSelection;
	
	@SuppressWarnings("unchecked")
	public BotDisplay(Frame f, int x, int y) {
		
		super(f, TowerMetaDisplay.FONT_HEIGHT + TowerMetaDisplay.LINE_HEIGHT + GAP * (1 + 2));
		
		setX(x);
		setY(y);
		
		towerMetaDisplay = new TowerMetaDisplay(f);
		
		//TOWER DISPLAY
		
		final int width = f.width;
		final int displaySpotWidth = (int) (TowerMetaDisplay.WIDTH_SPACE*3.3);
		displaySpotContainer = new ContentContainer<Container>(width - displaySpotWidth, 0, displaySpotWidth, heightInternal, displaySpot, GAP);
		
		getDisplaySpot().setContent(getTowerMetaDisplay());
		
		//TOWER SELECTION
		
		final TowerMeta<?>[] towerMetas = getGame().getTowerMetas();
		towerSelection = new ContentContainer[towerMetas.length];
		
		final int beginX = GAP, beginY = height()/2 - BUTTON_CONTAINER_SIZE/2;
		
		for(int i = 0; i < towerMetas.length; i++) {
			
			final TowerButton button = new TowerButton(towerMetas[i], getFrame());
			
			final ContentContainer<TowerButton> container = new ContentContainer<TowerButton>(beginX + i*(BUTTON_CONTAINER_SIZE + GAP), beginY, BUTTON_CONTAINER_SIZE, BUTTON_CONTAINER_SIZE, button, GAP/2 - TOWER_BUTTON_FREE_GAP);
			
			towerSelection[i] = container;

			container.pos.set(container.x + getX(), container.y + getY());
			
			button.pos.set(button.x + container.pos.getX(), button.y + container.pos.getY());
			
		}
		
	}
	
	@Override
	public void update(double t) {
		
		towerMetaDisplay.update(t);
		
		for(ContentContainer<TowerButton> c: towerSelection)
			c.getContent().update(t);
		
	}
	
	public void draw(Graphics2D g) {
		
		drawBorder(g);
		
		for(ContentContainer<TowerButton> c: towerSelection)
			c.draw(g);
		
		displaySpotContainer.draw(g);

	}
	
	private void drawBorder(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getFrame().width, BORDER);
		
		//TRANSLATE UNDER BORDER
		g.translate(0, BORDER);
		
		g.fillRect(displaySpotContainer.x, 0, Display.BORDER, height());
		
	}
	
	public TowerMetaDisplay getTowerMetaDisplay() {
		return towerMetaDisplay;
	}
	
	public DisplaySpot getDisplaySpot() {
		return displaySpot;
	}

}
