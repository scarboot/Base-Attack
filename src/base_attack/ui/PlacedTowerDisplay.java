package base_attack.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import base_attack.MapGenerator;
import base_attack.Tile;
import base_attack.Tower;

public class PlacedTowerDisplay extends Component implements Spot {
	
	private static final long serialVersionUID = 1L;
	
	public static final BufferedImage SELECTED = Images.loadImage("Selected");
	
	public static final int BUTTON_SIZE = 50 + 2*1;
	
	private final Frame f;
	private final Button[] buttons;
	
	private Tower tower;
	
	public PlacedTowerDisplay(Frame f, Position parent, Rectangle bounds) {
		
		super(parent, bounds);
		
		this.f = f;
		
		buttons = createButtons();
		
	}

	private Button[] createButtons() {
		
		if(getButtons() != null)
			throw new IllegalStateException();
		
		final Iterator<Rectangle> buttonBoundsIterator = getButtonBoundsIterator();
		
		final Button[] buttons = new Button[]{

				new DestroyButton(buttonBoundsIterator.next(), this, f)
				
		};
		
		return buttons;
		
	}

	private Iterator<Rectangle> getButtonBoundsIterator() {
		return new ButtonBoundsIterator(Display.GAP, new Dimension(BUTTON_SIZE, BUTTON_SIZE));
	}

	@Override
	public void drawContent(Graphics2D g) {
		
		if(getTower() == null)
			return;
		
		for(Button b: getButtons())
			b.draw(g);
		
	}

	public void update(double t) {
		
		checkTowerFocus();
		
		if(isFocused())
			for(Button b: getButtons())
				b.update(t);
		
	}
	
	private void checkTowerFocus() {
		
		if(tower != null && !tower.exists())
			setTower(null);
		
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
			
			Tower tower = f.getGame().getMap().getTiles()[x][y].getTower();
			
			if(tower == f.getGame().getBase())
				tower = null;
			
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
	public boolean isFocused() {
		return f.getBotDisplay().getDisplaySpot().getContent() == this;
	}
	
	@Override
	public void setFocused() {
		f.getBotDisplay().getDisplaySpot().setContent(this);
	}

	public boolean hasTower() {
		return getTower() != null;
	}
	
	public Button[] getButtons() {
		return buttons;
	}

}
