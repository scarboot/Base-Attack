package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import base_attack.MapGenerator;
import base_attack.Tile;
import base_attack.Tower;

public class PlacedTowerDisplay extends Container implements Spot {
	
	private static final long serialVersionUID = 1L;
	
	public static final BufferedImage SELECTED = Images.loadImage("Selected");
	
	private final Frame f;
	
	private Tower tower;
	
	public PlacedTowerDisplay(Frame f) {
		this.f = f;
	}

	@Override
	public void drawContent(Graphics2D g) {
		
		if(getTower() == null)
			return;
		
		g.setColor(Color.BLACK);
		Display.drawStringCentered(g, "Comming Soon", width/2, height/2);
		
	}

	public void update(double t) {
		
		if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE) || Mouse.isMeta() || !isFocused())
			setTower(null);
		
		if(f.getBotDisplay().getTowerMetaDisplay().isFocused() && f.getBotDisplay().getTowerMetaDisplay().getMeta() != null)
			return;
		
		{
			
			final Point pos = new Point(Mouse.getPos());
			pos.translate(0, -f.getTopDisplay().getTotalHeight());
			
			final int x = pos.x / Tile.SIZE;
			final int y = pos.y / Tile.SIZE;
			
			if(!(x >= 0 && x < MapGenerator.X && y >= 0 && y < MapGenerator.Y))
				return;
			
			final Tower tower = f.getGame().getMap().getTiles()[x][y].getTower();
			
			boolean clicked = Mouse.wasCleanDown();
			
			if(clicked) {
				
				if(tower != null)
					setFocused();
				
				setTower(tower);
				
			}
			
		}
		
	}
	
	public void drawOnGameBoard(Graphics2D g) {
		
		if(getTower() == null)
			return;
		
		final Tower t = getTower();
		
		final int x = t.getTile().x;
		final int y = t.getTile().y;
		
		final int rangeBeginX = (int)((x + 0.5 - t.getRange())*Tile.SIZE);
		final int rangeBeginY = (int)((y + 0.5 - t.getRange())*Tile.SIZE);
		final int diameter = (int) (t.getRange()*2*Tile.SIZE);
		
		g.drawImage(SELECTED, x*Tile.SIZE, y*Tile.SIZE, null);
		
		g.setColor(Color.RED);
		
		g.drawOval(rangeBeginX, rangeBeginY, diameter, diameter);
		
		
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	@Override
	public Position getPos() {
		return pos;
	}

	@Override
	public boolean isFocused() {
		return f.getBotDisplay().getDisplaySpot().getContent() == this;
	}
	
	@Override
	public void setFocused() {
		f.getBotDisplay().getDisplaySpot().setContent(this);
	}

}
