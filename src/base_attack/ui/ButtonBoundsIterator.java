package base_attack.ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Iterator;

public class ButtonBoundsIterator implements Iterator<Rectangle> {
	
	private final Dimension concreteSize;
	private final Rectangle pattern;
	private final int gap;
	
	public ButtonBoundsIterator(int gap, Dimension area, Dimension concreteSize) {

		this.pattern = new Rectangle(gap, gap, area.width, area.height);
		this.gap = gap;
		this.concreteSize = concreteSize;
		
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Rectangle next() {
		
		final int middleX = pattern.x + pattern.width/2;
		final int middleY = pattern.y + pattern.height/2;
		
		final int buttonX = middleX - concreteSize.width/2;
		final int buttonY = middleY - concreteSize.height/2;
		
		final Rectangle r = new Rectangle(buttonX, buttonY, concreteSize.width, concreteSize.height);
		
		pattern.x += pattern.width + gap;
		
		return r;
		
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
