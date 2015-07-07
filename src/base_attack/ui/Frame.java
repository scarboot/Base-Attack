package base_attack.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import base_attack.Game;
import base_attack.Tile;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private final Game game;
	private final BufferStrategy strat;
	private final int width, height;

	public Frame(Game game, int width, int height) {
		
		super("Base Attack");
		
		this.game = game;
		this.width = width;
		this.height = height;
		
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
		
		final Graphics rootGraphics = strat.getDrawGraphics();
		rootGraphics.create(getRootPane().getX(), getRootPane().getY(), width, height);
		
		final Graphics2D g = (Graphics2D) rootGraphics.create(getRootPane().getX(), getRootPane().getY(), width, height);
		
		draw(g);
		
		strat.show();
		
	}
	
	private void draw(Graphics2D g) {
		
		final Tile[][] tiles = game.getMap().getTiles();
		
		for(int x = 0; x < tiles.length; x++)
			for(int y = 0; y < tiles[x].length; y++)
				drawTile(tiles[x][y], x, y, g);
		
	}

	private void drawTile(Tile tile, int posX, int posY, Graphics2D g) {
		
		final int
		x = posX * tile.getWidth(),
		y = posY * tile.getHeight();
		
		g.drawImage(tile.getType().IMAGE, x, y, null);
		
	}

}











