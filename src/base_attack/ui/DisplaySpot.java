package base_attack.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DisplaySpot extends Container {
	
	private static final long serialVersionUID = 1L;
	
	private Spot content;
	
	public DisplaySpot() {
	}

	@Override
	public void drawContent(Graphics2D g) {
		
		if(hasContent())
			getContent().draw(g);
		
	}

	public void drawOnGameBoard(Graphics2D g) {
		
		if(hasContent())
			getContent().drawOnGameBoard(g);
		
	}

	public Spot getContent() {
		return content;
	}

	public void setContent(Spot spot) {
		
		if(hasContent())
			removeContent();
		
		if(spot != null) {
			
			spot.getPos().set(pos);
			
			if(spot instanceof Rectangle) {
				final Rectangle r = (Rectangle) spot;
				r.setSize(getSize());
			}
			
			this.content = spot;
			
		}
		
	}

	private boolean hasContent() {
		return getContent() != null;
	}

	private void removeContent() {
		content = null;
	}

}
