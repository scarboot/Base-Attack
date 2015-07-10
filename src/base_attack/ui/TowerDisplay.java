package base_attack.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import base_attack.TowerMeta;
import static base_attack.ui.Display.GAP;

public class TowerDisplay extends Container {
	
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
	
	private TowerMeta<?> meta;
	
	public TowerDisplay() {
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

}
