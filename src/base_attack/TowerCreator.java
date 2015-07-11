package base_attack;

public interface TowerCreator<T extends Tower> {
	
	public T getTower(Game g, Tile t);

}
