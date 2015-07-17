package base_attack;

import java.awt.Graphics2D;

public class Tile {
	
	public static final int SIZE = 50; // equals width and height
	
	public final int x, y;
	
	private TileType type;
    private Tower tower;
    
    public Tile(TileType type, int x, int y) {
    	this.type = type;
    	this.x = x;
    	this.y = y;
	}
    
    public Tile(int x, int y) {
    	this(TileType.STONE, x, y);
	}
	
	public TileType getType() {
		return type;
	}

    public void setType(TileType type){
        this.type = type;
    }

    public boolean hasTower() {
        return tower != null;
    }
    
    public Tower getTower() {
		return tower;
	}

    public void setTower(Tower t) {
    	
    	if(getType() != TileType.STONE)
    		throw new IllegalStateException("A Tower may only be placed on stone: " + getType() + " " + t);
    	
        tower = t;
        
    }

	public void draw(Graphics2D g) {
		
		getType().draw(g, x, y);
		
	}

	public void drawTower(Graphics2D g) {
		
		if(hasTower())
			getTower().draw(g);
		
	}

	public boolean canBuildTower() {
		
		return !hasTower() && getType() == TileType.STONE;
		
	}

	public void destroy(Tower t) {
		
		if(getTower() != t)
			throw new IllegalArgumentException();
		
		setTower(null);
		
	}
    
}
