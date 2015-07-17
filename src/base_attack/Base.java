package base_attack;

import java.awt.image.BufferedImage;

import base_attack.ui.Images;

/**
 * Created by Yannick on 07.07.2015.
 * Project: Base-Attack
 */
public class Base extends Tower {
	
	private static final int MAX_HITS = 3;
	
	private final BufferedImage[] damagedBaseImages = new BufferedImage[3];
	
	private int hits = 0;

	public Base(Game game, Tile tile) {
		
		super(game, tile, Double.POSITIVE_INFINITY, 0, 0);
		
		game.setBase(this);
		
		for(int i = 0; i < MAX_HITS; i++)
			getDamagedBaseImages()[i] = Images.loadImage(getClass().getSimpleName() + (i+1));
		
	}

	@Override
	public void update(double t) {
	}
	
	@Override
	public boolean shoot() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public BufferedImage getImage() {
		
		if(hits == 0)
			return super.getImage();
		else
			return getDamagedBaseImages()[hits - 1];
		
	}
	
	public void hit(int i) {
		
		hits += i;
		
		if(hits < 0)
			hits = 0;
		else if(hits > MAX_HITS)
			hits = MAX_HITS;

	}
	
	public void hit() {
		hit(1);
	}

	private BufferedImage[] getDamagedBaseImages() {
		return damagedBaseImages;
	}

	public boolean isDead() {
		return hits >= MAX_HITS;
	}
	
	@Override
	public void destroy() {
		throw new UnsupportedOperationException();
	}
	
}
