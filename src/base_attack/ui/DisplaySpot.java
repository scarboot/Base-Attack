package base_attack.ui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class DisplaySpot extends Component {

	private static final long serialVersionUID = 1L;
	
	private final PlacedTowerDisplay placedTowerDisplay;
	private final TowerMetaDisplay towerMetaDisplay;
	
	private Spot content;
	
	
	public DisplaySpot(Frame f, Position parent, Rectangle bounds) {
		
		super(parent, bounds);
		
		final Rectangle contentBounds = new Rectangle(0, 0, width - Display.BORDER, height);
		
		placedTowerDisplay = new PlacedTowerDisplay(f, getObviousPos(), contentBounds);
		towerMetaDisplay = new TowerMetaDisplay(f, getObviousPos(), contentBounds);
		
	}

	@Override
	public void drawContent(Graphics2D g) {
		
		g.fillRect(0, 0, Display.BORDER, height);
		g.translate(Display.BORDER, 0);
		
		if(hasContent())
			getContent().draw(g);
		
	}

	public void drawOnGameBoard(Graphics2D g) {
		
		if(hasContent())
			getContent().drawOnGameBoard(g);
		
	}
	
	private Position getObviousPos() {
		return new Position(getPos(), new Point(Display.BORDER, 0));
	}

	public Spot getContent() {
		return content;
	}

	public void setContent(Spot spot) {
		
		if(hasContent())
			removeContent();
		
		if(spot != null) {
			
			this.content = spot;
			
		}
		
	}

	private boolean hasContent() {
		return getContent() != null;
	}

	private void removeContent() {
		content = null;
	}
	
	public PlacedTowerDisplay getPlacedTowerDisplay() {
		return placedTowerDisplay;
	}

	public TowerMetaDisplay getTowerMetaDisplay() {
		return towerMetaDisplay;
	}

}
