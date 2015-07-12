package base_attack.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import base_attack.MapGenerator;
import base_attack.Tile;
import base_attack.TowerMeta;
import static base_attack.ui.Display.GAP;

public class TowerMetaDisplay extends Container implements Spot {
	
	private static final long serialVersionUID = 1L;
	
	public static final int
	FONT_HEIGHT = 35*2/3,
	FONT_WIDTH = FONT_HEIGHT*2/3;
	
	public static final double
	fontHeightActual = FONT_HEIGHT*2/3d;
	
	public static final Font FONT = new Font("Arial", Font.BOLD, FONT_HEIGHT);
	
	public static final BufferedImage
	RANGE = Images.loadImage("Range"),
	DAMAGE = Images.loadImage("Damage");
	
	public static final int
	LINE_HEIGHT = 50,
	HEIGHT_AND_GAP = LINE_HEIGHT + GAP,
	STRING_HEIGHT = LINE_HEIGHT/2,
	WIDTH_SPACE = LINE_HEIGHT + FONT_WIDTH*3 + GAP,
	FONT_HEIGHT_AND_GAP= FONT_HEIGHT + GAP;
	
	private final Frame f;
	private TowerMeta<?> meta;
	
	public TowerMetaDisplay(Frame f) {
		this.f = f;
	}

	public TowerMeta<?> getMeta() {
		return meta;
	}

	public void setMeta(TowerMeta<?> meta) {
		this.meta = meta;
	}

	@Override
	public void drawContent(Graphics2D g) {
		
		if(meta == null)
			return;
		
		g.setColor(Color.BLACK);
		
		{//NAME
			
			g.setColor(meta.getColor().darker());
			
			drawString(g, meta.getName(), 0, FONT_HEIGHT_AND_GAP/2);
			
			g.translate(0, FONT_HEIGHT_AND_GAP); //TRANSLATE
			
		}	
		
		{//PRICE
			
			Display.drawInCenter(g, TopDisplay.MONEY, LINE_HEIGHT);
			
			g.setColor(meta.getColor());
			drawString(g, meta.getPrice(), HEIGHT_AND_GAP, STRING_HEIGHT);
			g.setColor(Color.BLACK);
			
			g.translate(WIDTH_SPACE, 0); //TRANSLATE
			
		}	
		
		{//DAMAGE
			
			Display.drawInCenter(g, DAMAGE, LINE_HEIGHT);
			
			drawString(g, meta.getDamage(), HEIGHT_AND_GAP, STRING_HEIGHT);
			
			g.translate(WIDTH_SPACE, 0); //TRANSLATE
			
		}	
		
		{//RANGE
			
			Display.drawInCenter(g, RANGE, LINE_HEIGHT);
			
			drawString(g, meta.getRange(), HEIGHT_AND_GAP, STRING_HEIGHT);
			
		}
		
	}
	
	public static void drawString(Graphics2D g, Object o, int x, int middleY) {
		
		g = (Graphics2D) g.create(); //FONT CHANGES SHOULD STAY IN THIS METHOD
		
		g.setFont(FONT);
		
		final int y = (int) (middleY + fontHeightActual/2);
		
		g.drawString(String.valueOf(o), x, y);
		
	}

	public void update(double t) {
		
		if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE) || Mouse.isMeta() || !isFocused())
			setMeta(null);
		
		if(!isFocused())
			return;
		
		if(meta != null) {
			
			final Point pos = new Point(Mouse.getPos());
			pos.translate(0, -f.getTopDisplay().getTotalHeight());
			
			final int x = pos.x / Tile.SIZE;
			final int y = pos.y / Tile.SIZE;
			
			if(!(x >= 0 && x < MapGenerator.X && y >= 0 && y < MapGenerator.Y))
				return;
			
			if(Mouse.isCleanDown() && meta.isAllowed(x, y))
				meta.placeChecked(x, y);
			
		}
		
	}

	@Override
	public void drawOnGameBoard(Graphics2D g) {
		
		if(meta != null) {
			
			final Point pos = new Point(Mouse.getPos());
			
			if(!f.getGameArea().contains(pos))
				return;
			
			pos.translate(0, -f.getTopDisplay().getTotalHeight());
			
			final int x = pos.x / Tile.SIZE;
			final int y = pos.y / Tile.SIZE;
			
			if(!(x >= 0 && x < MapGenerator.X && y >= 0 && y < MapGenerator.Y)) //Should be useless
				return;
			
			final Color c = meta.isAllowed(x, y) ? new Color(0, 1f, 0, 0.3f) : new Color(1f, 0, 0, 0.3f);
			
			final int drawX = x*Tile.SIZE, drawY = y*Tile.SIZE;
			
			g.setColor(c);
			g.drawImage(meta.getImage(), drawX, drawY, null);
			g.fillRect(drawX, drawY, Tile.SIZE, Tile.SIZE);
			
			final int rangeBeginX = (int)((x + 0.5 - meta.getRange())*Tile.SIZE);
			final int rangeBeginY = (int)((y + 0.5 - meta.getRange())*Tile.SIZE);
			final int diameter = (int) (meta.getRange()*2*Tile.SIZE);
			
			g.setColor(Color.RED);
			
			g.drawOval(rangeBeginX, rangeBeginY, diameter, diameter);
			
		}
		
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
