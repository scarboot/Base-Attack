package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Component extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	private final Position parentPos = new Position();
	
	public Component(Position parent, Rectangle bounds) {
		
		if(parent != null)
			getParentPos().set(parent);
		
		if(bounds != null)
			getRelativeBounds().setBounds(bounds);
		
	}
	
	public Component(Component parent, Rectangle bounds) {
		this(parent != null ? parent.getPos() : null, bounds);
	}
	
	public Position getPos() {
		return new Position(getParentPos(), getLocation());
	}
	
	public void onInstall(Component c){};
	protected abstract void drawContent(Graphics2D g);
	
	public final void draw(Graphics2D g) {
		
		final Graphics2D contentGraphics = (Graphics2D) g.create(x, y, width, height);
		
		contentGraphics.setColor(Color.BLACK);
		
		drawContent(contentGraphics);
		
	}
	
	public Position getParentPos() {
		return parentPos;
	}
	
	public Rectangle getRelativeBounds() {
		return this;
	}

}
