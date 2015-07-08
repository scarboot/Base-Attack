package base_attack;

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
    
}
