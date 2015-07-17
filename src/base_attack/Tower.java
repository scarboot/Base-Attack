package base_attack;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import base_attack.ui.Images;

public abstract class Tower implements Updateable {
	
	public static final BufferedImage REPLACE_INDICATOR = Images.loadImage("ReplaceIndicator");
	
	private final BufferedImage image;
	
	private final Game game;
	private final Tile tile;	
	private final Cooldown cooldown;
	private final double range;
	private final int refund;
	private final TowerMeta<? extends Tower> meta;
	
	public Tower(Game game, Tile tile, double cooldown, double range, int refund, TowerMeta<? extends Tower> meta) {
		
		this.game = game;
		this.tile = tile;
		this.cooldown = new Cooldown(cooldown);
		this.range = range;
		this.refund = refund;
		this.meta = meta;
		
		image = Images.loadImage(getClass().getSimpleName());
	}
	
	@Override
	public void update(double t) {
		
		getCooldown().update(t);
		
		if(getCooldown().ready()) {
			
			if(shoot())
				getCooldown().use();
			
		}
		
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public abstract boolean shoot();

	public double getRange() {
		return range;
	}

	private Cooldown getCooldown() {
		return cooldown;
	}
	
	public Game getGame() {
		return game;
	}
	
	public Mob findMob() {
		
		final double maxDistanceSq = Math.pow(getRange(), 2);
		
		double bestDistanceSq = Double.POSITIVE_INFINITY;
		Mob bestMob = null;
		
		for(Mob m: getGame().getMap().getMobs()) {
			
			final double distanceSq = m.getLocation().distanceSq(getTile().x, getTile().y);
			
			if(distanceSq > maxDistanceSq)
				continue;
			
			if(distanceSq < bestDistanceSq) {
				bestDistanceSq = distanceSq;
				bestMob = m;
			}
			
		}
		
		return bestMob;
		
	}
	
	public void draw(Graphics2D g) {
		
		final int x = getTile().x * Tile.SIZE;
		final int y = getTile().y * Tile.SIZE;
		
		g.drawImage(getImage(), x, y, null);
		
		if(canBeReplacedBy(getGame().getFrame().getBotDisplay().getTowerMetaDisplay().getMeta()))
			g.drawImage(REPLACE_INDICATOR, x, y, null);

	}

	private boolean canBeReplacedBy(TowerMeta<?> meta) {
		
		if(getMeta() == null || meta == null)
			return false;
		
		return getMeta().getPriceSimpel() < meta.getPriceSimpel() && ((meta.getPriceSimpel() - getRefund()) <= getGame().getMoney());
		
	}

	public boolean exists() {
		return getTile().getTower() == this;
	}

	public int getRefund() {
		return refund;
	}

	public void destroy() {
		
		getTile().destroy(this);
		getGame().addMoney(getRefund());
		
	}

	public TowerMeta<? extends Tower> getMeta() {
		return meta;
	}
	
}
