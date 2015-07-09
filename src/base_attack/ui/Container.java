package base_attack.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Container {
	
	private final Rectangle bounds;
	
	public Container(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void onInstall(Container c){};
	
	public void draw(Graphics2D g) {
		
		g.create(bounds.x, bounds.y, bounds.width, bounds.height);
		
	}

}
