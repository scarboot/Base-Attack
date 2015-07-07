package base_attack;

public class Tile {
	
	int height, width;
	TileType type;
    Tower tower;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
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

    public void setTower(Tower t) {
        tower = t;
    }
    
}
