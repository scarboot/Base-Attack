package base_attack.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	public static final Keyboard INSTANCE = new Keyboard();
	
	private static final boolean[] keys = new boolean[1024];
	
	private Keyboard() {
	}
	
	public static boolean isKeyDown(int keyValue){
		
		if(keyValue >= 0 && keyValue < keys.length)
			return keys[keyValue];
		else
			return false;
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		final int keyCode = e.getKeyCode();
		
		if(keyCode >= 0 && keyCode < keys.length)
			keys[keyCode] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		final int keyCode = e.getKeyCode();
		
		if(keyCode >= 0 && keyCode < keys.length)
			keys[keyCode] = false;
		
	}

	//Unused
	@Override
	public void keyTyped(KeyEvent g) {}
	
}