package base_attack.ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Iterator;

public class ButtonBoundsIterator implements Iterator<Rectangle> {
	
	private final Rectangle pattern;
	private final int gap;
	
	public ButtonBoundsIterator(int gap, Dimension size) {

		this.pattern = new Rectangle(gap, gap, size.width, size.height);
		this.gap = gap;
		
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Rectangle next() {
		
		final Rectangle r = new Rectangle(pattern);
		
		pattern.x += r.width + gap;
		
		return r;
		
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
