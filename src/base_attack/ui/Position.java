package base_attack.ui;

import java.awt.Point;

public class Position {
	
	public int x, y;
	
	public Position() {
	}
	
	public Position(Position parentPos, Point location) {
		setX(parentPos.x + location.x);
		setY(parentPos.y + location.y);
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void set(int x, int y) {
		setX(x);
		setY(y);
	}

	public void set(Position pos) {
		set(pos.x, pos.y);
	}

}
