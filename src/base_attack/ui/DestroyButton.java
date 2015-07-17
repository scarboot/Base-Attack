package base_attack.ui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import base_attack.Updateable;

import static base_attack.ui.Display.*;
import static base_attack.ui.PlacedTowerDisplay.*;

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
	protected void drawButtonInfo(Graphics2D g, Dimension d) {
		
		g.setFont(PlacedTowerDisplay.FONT);
		
		final String money = String.valueOf(getDisplay().getTower().getRefund());
		final int moneyDisplayWidth = (int) (getFontWidth(g.getFont())*money.length());
		final int totalWidth = moneyDisplayWidth + MONEY_SMALL.getWidth() + GAP;
		
		drawCentered(g, MONEY_SMALL, d.width/2 - totalWidth/2 + MONEY_SMALL.getWidth()/2, d.height/2);
		drawString(g, money, d.width/2 - totalWidth/2 + MONEY_SMALL.getWidth() + GAP, d.height/2);
		
	}
	
	@Override
	public BufferedImage getImage() {
		return BULLDOZER;
	}
	
	public PlacedTowerDisplay getDisplay() {
		return display;
	}

}
