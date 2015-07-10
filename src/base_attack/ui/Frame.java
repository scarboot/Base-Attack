package base_attack.ui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import base_attack.Bullet;
import base_attack.Game;
import base_attack.Mob;
import base_attack.Tile;
import base_attack.Updateable;

public class Frame extends JFrame implements Updateable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private final Game game;
	private final BufferStrategy strat;
	public final int width, height, gameHeight;
	
	private final TopDisplay topDisplay;
	private final BotDisplay botDisplay;

	public Frame(Game game, int width, int gameHeight) {
		
		super("Base Attack");
		
		this.game = game;
		
		this.width = width;
		this.gameHeight = gameHeight;
		
		this.topDisplay = new TopDisplay(this);
		this.botDisplay = new BotDisplay(this);
		
		this.height = topDisplay.getTotalHeight() + gameHeight + botDisplay.getTotalHeight();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFocusable(true);
		setResizable(false);
		setLayout(null);
		
		addKeyListener(this);
		
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
	
	private void drawBotMenu(Graphics2D g) {
		
		botDisplay.draw(g);
		
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
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			getBotDisplay().getTowerDisplay().setMeta(null);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void update(double t) {
		getTopDisplay().update(t);
		getBotDisplay().update(t);
	}

}











