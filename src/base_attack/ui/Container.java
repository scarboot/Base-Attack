package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Container extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	public final Position pos = new Position();
	
	public Container() {
	}
	
	public Container(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void onInstall(Container c){};
	public abstract void drawContent(Graphics2D g);
	
	public final void draw(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		
		final Graphics2D contentGraphics = (Graphics2D) g.create(x, y, width, height);
		
		drawContent(contentGraphics);
		
	}

}
