package base_attack.ui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import base_attack.Bullet;
import base_attack.Game;
import base_attack.Mob;
import base_attack.PointDouble;
import base_attack.Tile;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private final Game game;
	private final BufferStrategy strat;
	public final int width, height, gameHeight;
	
	private final TopDisplay topDisplay;
	private final BotDisplay botDisplay;

	public Frame(Game game, int width, int gameHeight) {
		
		super("Base Attack");
		
		this.game = game;
		
		this.topDisplay = new TopDisplay(this);
		this.botDisplay = new BotDisplay(this);
		
		this.width = width;
		this.gameHeight = gameHeight;
		this.height = topDisplay.getTotalHeight() + gameHeight + botDisplay.getTotalHeight();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFocusable(true);
		setResizable(false);
		setLayout(null);
		
		//addKeyListener(new Keyboard());
		
		//final Mouse m = new Mouse();
		//addMouseListener(m);
		//addMouseMotionListener(m);
		
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
		subG = (Graphics2D) g.create(0, 0, width, topDisplay.getTotalHeight());
		drawTopDisplay(subG);
		
		//Game
		subG = (Graphics2D) g.create(0, topDisplay.getTotalHeight(), width, gameHeight);
		drawGame(subG);
		
		//Bottom menu
		subG = (Graphics2D) g.create(0, topDisplay.getTotalHeight() + gameHeight, width, botDisplay.getTotalHeight());
		drawBotMenu(subG);
		
	}

	private void drawTopDisplay(Graphics2D g) {
		
		topDisplay.draw(g);
		
	}

	private void drawGame(Graphics2D g) {
		
		//Draw Tiles
		
		final Tile[][] tiles = game.getMap().getTiles();
		
		for(int x = 0; x < tiles.length; x++)
			for(int y = 0; y < tiles[x].length; y++)
				drawTile(tiles[x][y], g, false);
		
		//Draw Mobs
		
		for(Mob m: game.getMap().getMobs())
			drawMob(m, g);
		
		//Draw Towers
		
		for(int x = 0; x < tiles.length; x++)
			for(int y = 0; y < tiles[x].length; y++)
				drawTile(tiles[x][y], g, true);
		
		//Draw Bullets
		
		for(Bullet b: getGame().getMap().getBullets())
			b.draw(g);
		
	}
	
	private void drawBotMenu(Graphics2D g) {
		
		botDisplay.draw(g);
		
	}

	private void drawTile(Tile tile, Graphics2D g, boolean drawTower) {
		
		final int
		x = tile.x * Tile.SIZE,
		y = tile.y * Tile.SIZE;
		
		//Draw TileType
		
		if(!drawTower)
			g.drawImage(tile.getType().IMAGE, x, y, null);
		
		//Draw Tower
		
		if(tile.hasTower() && drawTower)
			g.drawImage(tile.getTower().getImage(), x, y, null);
		
	}

	private void drawMob(Mob m, Graphics2D g) {
		
		final PointDouble p = m.getMovement().getExactLocation();
		final int x = (int) (p.x * Tile.SIZE), y = (int) (p.y*Tile.SIZE);
		
		g.drawImage(m.getImage(), x, y, null);
		
	}
	
	public Game getGame() {
		return game;
	}

}











