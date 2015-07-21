package base_attack.ui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import base_attack.Game;
import base_attack.Updateable;

public abstract class Display extends Component implements Updateable {
	
	//CONSTANTS
	
	private static final long serialVersionUID = 1L;

	public static final int
	BORDER = 3,
	GAP = 10;
	
	//FONT STUFF
	
	public static final int
	FONT_HEIGHT = 40,
	FONT_WIDTH = FONT_HEIGHT*2/3;
	
	public static final double
	fontHeightActual = FONT_HEIGHT*2/3d;
	
	public static final Font FONT = new Font("Arial", Font.PLAIN, FONT_HEIGHT);
	
	//REAL CONTENT
	
	private final Frame frame;
	
	public final int space, heightInternal;
	
	public Display(Frame frame, int y, int heightInternal) {
		super(new Position(), new Rectangle(0, y, frame.width, heightInternal + BORDER));
		this.frame = frame;
		this.heightInternal = heightInternal;
		this.space = heightInternal + GAP;
	}
	
	public Display(Frame frame, int y) {
		this(frame, 0, y);
	}

	public Frame getF() {
		return frame;
	}
	
	public Game getGame() {
		return frame.getGame();
	}
	
	public static void drawString(Graphics2D g, Object o, int x, int middleY) {
		
		final int y = (int) (middleY + getFontHeight(g)/2);
		
		g.drawString(String.valueOf(o), x, y);
		
	}

	public static void drawStringCentered(Graphics2D g, Object o, int middleX, int middleY) {
		
		final String print = String.valueOf(o);
		
		final int x = (int) (middleX - print.length()*getFontWidth(g)*0.5);
		final int y = (int) (middleY + getFontHeight(g)/2);
		
		g.drawString(print, x, y);
		
	}

	public static void drawInCenter(Graphics2D g, BufferedImage image, int x, int y, int height, int width) {
		drawCentered(g, image, x + width/2, y + height/2);
		
	}

	public void drawInCenter(Graphics2D g, BufferedImage image, int x, int y) {
		drawInCenter(g, image, x, y, height(), height());
	}
	
	public static void drawCentered(Graphics2D g, BufferedImage image, int middleX, int middleY) {
		
		final int x = middleX - image.getWidth()/2;
		final int y = middleY - image.getHeight()/2;
		
		g.drawImage(image, x, y, null);
		
	}
	
	public int height() {
		return heightInternal;
	}
	
	public int getTotalHeight() {
		return height;
	}
	
	public int space() {
		return space;
	}
	
	public int middle() {
		return height()/2;
	}

	public static void drawInCenter(Graphics2D g, BufferedImage image, int height) {
		drawInCenter(g, image, 0, 0, height, height);		
	}

	@Override
	public void update(double t) {
	}
	
	public static double getFontHeight(Font f) {
		return f.getSize2D()*2/3;
	}
	
	private static double getFontHeight(Graphics2D g) {
		return getFontHeight(g.getFont());
	}
	
	public static double getFontWidth(Font f) {
		return f.getSize2D()*0.9*2/3;
	}
	
	private static double getFontWidth(Graphics2D g) {
		return getFontWidth(g.getFont());
	}

}
