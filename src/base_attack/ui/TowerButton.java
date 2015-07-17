package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import base_attack.TowerMeta;
import base_attack.Updateable;
import static base_attack.ui.BotDisplay.TOWER_BUTTON_FREE_GAP;

public class TowerButton extends Button implements Updateable {
	
	private static final long serialVersionUID = 1L;
	
	private final Frame frame;
	private final TowerMeta<?> meta;

	public TowerButton(Position parent, Rectangle bounds, Frame frame, TowerMeta<?> meta) {
		super(parent, bounds);
		this.frame = frame;
		this.meta = meta;
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
	public void onClick() {
		focus();
	}
	
	private void focus() {
		frame.getBotDisplay().getTowerMetaDisplay().setFocused();
		frame.getBotDisplay().getTowerMetaDisplay().setMeta(getMeta());
	}

}
