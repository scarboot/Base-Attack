package base_attack.ui;

import java.awt.event.MouseEvent;

public class MouseHandleEvent {
	
	public final MouseEventType type;
	public final MouseEvent event;
	
	public MouseHandleEvent(MouseEventType type, MouseEvent event) {
		this.type = type;
		this.event = event;
	}

}
