package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import base_attack.TowerMeta;

public class BotDisplay extends Display {
	
	private static final long serialVersionUID = 1L;
	
	public static final int BUTTON_CONTAINER_SIZE = 60, TOWER_BUTTON_FREE_GAP = 3;
	public static final int BUTTON_SIZE = 50 + 2*3;
	
	private final DisplaySpot displaySpot;
	private final TowerButton[] towerSelection;
	
	public BotDisplay(Frame f, int y) {
		
		super(f, y, TowerMetaDisplay.FONT_HEIGHT + TowerMetaDisplay.LINE_HEIGHT + GAP * (1 + 2));
		
		//TOWER DISPLAY
		
		{
			
			final int displaySpotWidth = (int) (TowerMetaDisplay.WIDTH_SPACE*3.3);
			final int spotX = f.width - displaySpotWidth;
			
			displaySpot = new DisplaySpot(f, getObviousPos(), new Rectangle(spotX, 0, displaySpotWidth, height()));
			
			getDisplaySpot().setContent(getTowerMetaDisplay());
			
		}
		
		//TOWER SELECTION
		
		final TowerMeta<?>[] towerMetas = getGame().getTowerMetas();
		towerSelection = new TowerButton[towerMetas.length];
		
		final int beginX = GAP, beginY = height()/2 - BUTTON_SIZE/2;
		int addX = 0;
		
		for(int i = 0; i < towerMetas.length; i++) {
			
			final TowerButton button = new TowerButton(getObviousPos(), new Rectangle(beginX + addX, beginY, BUTTON_SIZE, BUTTON_SIZE), getF(), towerMetas[i]);
			
			towerSelection[i] = button;
			
			addX += BUTTON_CONTAINER_SIZE + GAP/2;
			
		}
		
	}
	
	private Position getObviousPos() {
		return new Position(getPos(), new Point(0, Display.BORDER));
	}

	@Override
	public void update(double t) {
		
		getTowerMetaDisplay().update(t);
		getPlacedTowerDisplay().update(t);
		
		for(TowerButton b: towerSelection)
			b.update(t);
		
	}
	
	public void drawContent(Graphics2D g) {
		
		drawBorder(g);
		
		for(TowerButton b: towerSelection)
			b.draw(g);
		
		getDisplaySpot().draw(g);

	}
	
	private void drawBorder(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getF().width, BORDER);
		
		//TRANSLATE UNDER BORDER
		g.translate(0, BORDER);
		
	}
	
	public TowerMetaDisplay getTowerMetaDisplay() {
		return getDisplaySpot().getTowerMetaDisplay();
	}
	
	public DisplaySpot getDisplaySpot() {
		return displaySpot;
	}

	public PlacedTowerDisplay getPlacedTowerDisplay() {
		return getDisplaySpot().getPlacedTowerDisplay();
	}

}
