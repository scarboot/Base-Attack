package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import base_attack.TowerMeta;
import base_attack.Updateable;
import static base_attack.ui.BotDisplay.TOWER_BUTTON_FREE_GAP;

public class TowerButton extends Container implements Updateable {
	
	private static final long serialVersionUID = 1L;
	
	private final Frame frame;
	private final TowerMeta<?> meta;
	
	public TowerButton(TowerMeta<?> meta, Frame frame) {
		this.meta = meta;
		this.frame = frame;
	}

	@Override
	public void drawContent(Graphics2D g) {
		
		g.translate(TOWER_BUTTON_FREE_GAP, TOWER_BUTTON_FREE_GAP);
		
		final int gap = TOWER_BUTTON_FREE_GAP + (isFocued() ? 0 : -1);
		
		g.setColor(Color.BLACK);
		g.fillRect(-gap, -gap, width-TOWER_BUTTON_FREE_GAP*2+gap*2, height-TOWER_BUTTON_FREE_GAP*2+gap*2);
		
		g.setColor(meta.canBuy() ? Color.WHITE : Color.GRAY);
		g.fillRect(0, 0, width-TOWER_BUTTON_FREE_GAP*2, height-TOWER_BUTTON_FREE_GAP*2);
		
		g.drawImage(meta.getImage(), 0, 0, null);
		
	}

	public TowerMeta<?> getMeta() {
		return meta;
	}
	
	public boolean isFocued() {
		return frame.getBotDisplay().getTowerMetaDisplay().getMeta() == getMeta();
	}

	@Override
	public void update(double t) {
		
		synchronized (Mouse.getEvents()) {
			
			for(MouseHandleEvent e: Mouse.getEvents(MouseEventType.CLICK)) {
				
				if(containsPoint(e.event.getPoint()))
					focus();
				
			}
			
		}
		
	}
	
	private void focus() {
		frame.getBotDisplay().getTowerMetaDisplay().setMeta(getMeta());
	}

	public boolean containsPoint(Point p) {
		
		Point p2 = new Point(p);
		p2.translate(-pos.getX()+x, -pos.getY()+y);
		return super.contains(p2);
		
	}

}
