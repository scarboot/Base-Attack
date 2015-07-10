package base_attack.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import base_attack.Updateable;

public class Mouse implements Updateable, MouseMotionListener, MouseListener{
	
	public static final Mouse INSTANCE = new Mouse();
	
	private static final List<MouseHandleEvent> events = Collections.synchronizedList(new ArrayList<MouseHandleEvent>());
	
	private static Point pos = new Point(0, 0);
	
	private static boolean down, alt, altgr, meta, shift;
	
	private Mouse() {
	}

	public static boolean isDown() {
		return down;
	}

	public static void setDown(boolean down) {
		Mouse.down = down;
	}

	public static boolean isAlt() {
		return alt;
	}

	public static void setAlt(boolean alt) {
		Mouse.alt = alt;
	}

	public static boolean isAltgr() {
		return altgr;
	}

	public static void setAltgr(boolean altgr) {
		Mouse.altgr = altgr;
	}

	public static boolean isMeta() {
		return meta;
	}

	public static void setMeta(boolean meta) {
		Mouse.meta = meta;
	}

	public static boolean isShift() {
		return shift;
	}

	public static void setShift(boolean shift) {
		Mouse.shift = shift;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		getEvents().add(new MouseHandleEvent(MouseEventType.ENTER, e));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		getEvents().add(new MouseHandleEvent(MouseEventType.EXIT, e));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		getEvents().add(new MouseHandleEvent(MouseEventType.PRESS, e));
		setDown(true);
		setMouseMeta(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		getEvents().add(new MouseHandleEvent(MouseEventType.RELEASE, e));
		setDown(false);
		setMouseMeta(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		getEvents().add(new MouseHandleEvent(MouseEventType.DRAG, e));
		mouseMoved(e);
		mouseClicked(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		getEvents().add(new MouseHandleEvent(MouseEventType.MOVE, e));
		pos = e.getPoint();
	}
	
	private void setMouseMeta(MouseEvent e){
		setAlt(e.isAltDown());
		setAltgr(e.isAltGraphDown());
		setMeta(e.isMetaDown());
		setShift(e.isShiftDown());
	}
	
	public static Point getPosOnScreen() {
		return pos;
	}

	public static Point getPos(Point relation) {
		return new Point(pos.x - relation.x, pos.y - relation.y);
	}
	
	public static boolean isCleanDown(){
		
		return isDown() && !isAlt() && !isAltgr() && !isMeta() && !isShift();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		getEvents().add(new MouseHandleEvent(MouseEventType.CLICK, e));
	}

	@Override
	public void update(double t) {
		events.clear();
	}
	
	public Iterable<MouseHandleEvent> getEvents(final MouseEventType type) {
		
		return new Iterable<MouseHandleEvent>() {
			
			@Override
			public Iterator<MouseHandleEvent> iterator() {
				return new MouseHandleEventIterator(type);
			}
			
		};
		
	}
	
	public static List<MouseHandleEvent> getEvents() {
		return events;
	}
	
	private static final class MouseHandleEventIterator implements Iterator<MouseHandleEvent> {
		
		private final MouseEventType type;
		private final Iterator<MouseHandleEvent> it = getEvents().iterator();
		
		private MouseHandleEvent next;
		
		public MouseHandleEventIterator(MouseEventType type) {
			
			this.type = type;
			nextInternal();
			
		}
		
		private void nextInternal() {
			
			next = null;
			
			while(it.hasNext() && next == null) {
				
				next = it.next();
				
				if(next.type != type)
					next = null;
				
			}
			
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public MouseHandleEvent next() {
			
			if(next == null)
				throw new NoSuchElementException();
			
			return next;
			
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

}
