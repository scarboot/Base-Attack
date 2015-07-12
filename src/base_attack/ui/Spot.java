package base_attack.ui;

import java.awt.Graphics2D;

public interface Spot {
	
	public void drawOnGameBoard(Graphics2D g);
	public Position getPos();
	public void draw(Graphics2D g);
	public void drawContent(Graphics2D g);
	public boolean isFocused();
	public void setFocused();

}
