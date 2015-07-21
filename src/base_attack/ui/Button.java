package base_attack.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import base_attack.Updateable;

public abstract class Button extends Component implements Updateable {
	
	public Button(Component parent, Rectangle bounds) {
		super(parent, bounds);
	}
	
	public Button(Position parent, Rectangle bounds) {
		super(parent, bounds);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void drawContent(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		final BufferedImage image = getImage();
		
		if(image == null)
			return;
		
		Display.drawCentered(g, image, width/2, height/2);
		
	}
	
	@Override
	public void update(double t) {
		
		synchronized (Mouse.getEvents()) {
			
			for(MouseHandleEvent e: Mouse.getEvents(MouseEventType.CLICK)) {
				
				if(!Mouse.isClean(e.event))
					continue;
				
				if(containsPoint(e.event.getPoint()))
					onClick();
				
			}
			
		}
		
	}

	public abstract void onClick();

	public boolean containsPoint(Point p) {
		
		final Point p2 = new Point(p);
		
		p2.translate(-getParentPos().getX(), -getParentPos().getY());
		
		return contains(p2);
		
	}
	
	public BufferedImage getImage() {
		throw null;
	}

	public final void drawButtonInformation(Graphics2D g, Dimension size, int y) {
		
		final int middleX = this.x + width/2;
		final int middleY = this.y + height/2 + y;

		final int drawX = middleX - size.width/2;
		final int drawY = middleY - size.height/2;
		
		final Graphics2D subG = (Graphics2D) g.create(drawX, drawY, size.width, size.height);
		
		drawButtonInfo(subG, size);
		
	}

	protected void drawButtonInfo(Graphics2D g, Dimension d) {
	}

}
