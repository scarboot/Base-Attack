package base_attack.ui;

import java.awt.Graphics2D;

public class DisplaySpot extends Container implements Spot {
	
	private static final long serialVersionUID = 1L;
	
	private Spot content;
	
	public DisplaySpot() {
	}

	@Override
	public void drawContent(Graphics2D g) {
		
		if(hasSpot())
			getContent().drawContent(g);
		
	}

	public void drawOnGameBoard(Graphics2D g) {
		
		if(hasSpot())
			getContent().drawOnGameBoard(g);
		
	}

	public Spot getContent() {
		return content;
	}

	public void setContent(Spot spot) {
		
		if(hasSpot())
			removeContent();
		
		if(spot != null) {
			
			spot.getPos().set(pos);
			this.content = spot;
			
		}
		
	}

	private boolean hasSpot() {
		return getContent() != null;
	}

	private void removeContent() {
		content = null;
	}

	@Override
	public Position getPos() {
		return pos;
	}

}
