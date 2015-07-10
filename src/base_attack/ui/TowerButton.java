package base_attack.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import base_attack.TowerMeta;

public class TowerButton extends Container {
	
	private static final long serialVersionUID = 1L;
	
	public static final BasicStroke STROKE = new BasicStroke(2);
	
	private final TowerMeta<?> meta;
	
	public TowerButton(TowerMeta<?> meta) {
		this.meta = meta;
	}

	@Override
	public void drawContent(Graphics2D g) {
		
		g = (Graphics2D) g.create();
		
		g.setStroke(STROKE);
		
		g.setColor(meta.getColor());
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.WHITE);
		g.fillRect(2, 2, width-4, height-4);
		
		g.drawImage(meta.getImage(), 2, 2, null);
		
	}

	public TowerMeta<?> getMeta() {
		return meta;
	}

}
