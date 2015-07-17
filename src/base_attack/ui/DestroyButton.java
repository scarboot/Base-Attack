package base_attack.ui;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import base_attack.Updateable;

public class DestroyButton extends Button implements Updateable {
	
	private static final long serialVersionUID = 1L;
	
	public static final BufferedImage BULLDOZER = Images.loadImage("Bulldozer");

	private final PlacedTowerDisplay display;
	private final Frame frame;
	
	public DestroyButton(Rectangle bounds, PlacedTowerDisplay display, Frame frame) {
		super(display, bounds);
		this.display = display;
		this.frame = frame;
	}

	public boolean canBeClicked() {
		return frame.getBotDisplay().getDisplaySpot().getContent() == getDisplay();
	}
	
	@Override
	public void onClick() {
		
		if(!canBeClicked())
			return;
		
		if(!getDisplay().hasTower())
			return;
		
		getDisplay().getTower().destroy();
		getDisplay().setTower(null);
		
	}
	
	@Override
	public BufferedImage getImage() {
		return BULLDOZER;
	}
	
	public PlacedTowerDisplay getDisplay() {
		return display;
	}

}
