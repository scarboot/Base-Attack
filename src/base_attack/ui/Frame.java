package base_attack.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import base_attack.Bullet;
import base_attack.Game;
import base_attack.MapGenerator;
import base_attack.Mob;
import base_attack.Tile;
import base_attack.TowerMeta;
import base_attack.Updateable;

public class Frame extends JFrame implements Updateable {
	
	private static final long serialVersionUID = 1L;
	
	private final Game game;
	private final BufferStrategy strat;
	public final int width, height, gameHeight;
	
	private final TopDisplay topDisplay;
	private final BotDisplay botDisplay;
	
	private final Rectangle gameArea;

	public Frame(Game game, int width, int gameHeight) {
		
		super("Base Attack");
		
		this.game = game;
		
		this.width = width;
		this.gameHeight = gameHeight;
		
		this.topDisplay = new TopDisplay(this);
		this.botDisplay = new BotDisplay(this, 0, topDisplay.getTotalHeight() + gameHeight);
		
		this.height = topDisplay.getTotalHeight() + gameHeight + botDisplay.getTotalHeight();
		
		gameArea = new Rectangle(0, topDisplay.getTotalHeight(), width, gameHeight);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFocusable(true);
		setResizable(false);
		setLayout(null);
		
		addKeyListener(Keyboard.INSTANCE);
		
		getRootPane().addMouseListener(Mouse.INSTANCE);
		getRootPane().addMouseMotionListener(Mouse.INSTANCE);
		
		setLocation(-width*2, -height*2);
		setVisible(true);
		setSize(getRootPane().getX() + width + getRootPane().getX(), getRootPane().getY() + height + getRootPane().getX());
		
		{
			
			BufferStrategy strat = null;
			
			while(strat == null) {
				
				createBufferStrategy(2);
				strat = getBufferStrategy();
				
			}
			
			this.strat = strat;
			
		}
		
		setLocationRelativeTo(null);
		
	}
	
	public void draw() {
		
		final Graphics2D rootGraphics = (Graphics2D) strat.getDrawGraphics();
		
		rootGraphics.setFont(Display.FONT);
		rootGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		rootGraphics.clearRect(0, 0, getWidth(), getHeight());
		
		final Graphics2D g = (Graphics2D) rootGraphics.create();
		g.translate(getRootPane().getX(), getRootPane().getY());
		
		draw(g);
		
		g.dispose();
		rootGraphics.dispose();
		
		strat.show();
		
	}
	
	private void draw(Graphics2D g) {
		
		Graphics2D subG;
		
		//Top display
		subG = (Graphics2D) g.create(topDisplay.getX(), topDisplay.getY(), width, topDisplay.getTotalHeight());
		topDisplay.draw(subG);
		
		//Game
		subG = (Graphics2D) g.create(0, topDisplay.getTotalHeight(), width, gameHeight);
		drawGame(subG);
		
		//Tower Building
		drawTowerBuilding(subG);
		
		//Bottom menu
		subG = (Graphics2D) g.create(botDisplay.getX(), botDisplay.getY(), width, botDisplay.getTotalHeight());
		botDisplay.draw(subG);
		
	}

	private void drawTowerBuilding(Graphics2D subG) {
		
		final TowerMeta<?> meta = getBotDisplay().getTowerDisplay().getMeta();
		
		if(meta != null) {
			
			final Point pos = new Point(Mouse.getPos());
			
			if(!getGameArea().contains(pos))
				return;
			
			pos.translate(0, -getTopDisplay().getTotalHeight());
			
			final int x = pos.x / Tile.SIZE;
			final int y = pos.y / Tile.SIZE;
			
			if(!(x >= 0 && x < MapGenerator.X && y >= 0 && y < MapGenerator.Y)) //Should be useless
				return;
			
			final Color c = meta.isAllowed(x, y) ? new Color(0, 1f, 0, 0.3f) : new Color(1f, 0, 0, 0.3f);
			
			final int drawX = x*Tile.SIZE, drawY = y*Tile.SIZE;
			
			subG.setColor(c);
			subG.drawImage(meta.getImage(), drawX, drawY, null);
			subG.fillRect(drawX, drawY, Tile.SIZE, Tile.SIZE);
			
		}
		
	}

	private void drawGame(Graphics2D g) {
		
		//Draw Tiles
		
		final Tile[][] tiles = game.getMap().getTiles();
		
		for(int x = 0; x < tiles.length; x++)
			for(int y = 0; y < tiles[x].length; y++)
				tiles[x][y].draw(g);
		
		//Draw Mobs
		
		for(Mob m: game.getMap().getMobs())
			m.draw(g);
		
		//Draw Towers
		
		for(int x = 0; x < tiles.length; x++)
			for(int y = 0; y < tiles[x].length; y++)
				tiles[x][y].drawTower(g);
		
		//Draw Bullets
		
		for(Bullet b: getGame().getMap().getBullets())
			b.draw(g);
		
	}
	
	public Game getGame() {
		return game;
	}
	
	public TopDisplay getTopDisplay() {
		return topDisplay;
	}
	
	public BotDisplay getBotDisplay() {
		return botDisplay;
	}
	
	@Override
	public void update(double t) {
		getTopDisplay().update(t);
		getBotDisplay().update(t);
	}

	public Rectangle getGameArea() {
		return gameArea;
	}

}











