package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import base_attack.Tile;
import base_attack.Tower;

public class PlacedTowerDisplay extends Container implements Spot {
	
	private static final long serialVersionUID = 1L;
	
	private Tower tower;
	
	public PlacedTowerDisplay() {
	}

	@Override
	public void drawContent(Graphics2D g) {
		
		if(tower == null)
			return;
		
		g.setColor(Color.BLACK);
		
		g.fillRect(0, 0, 100, 100);
		
	}

	public void update(double t) {
		
		if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE) || Mouse.isMeta())
			setTower(null);
		
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

}
