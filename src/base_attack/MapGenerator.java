package base_attack;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Yannick on 07.07.2015.
 * Project: Base-Attack
 */
public class MapGenerator {

    public static final int X = 15, Y = 9, DIRTAMOUNT = 10;
    public static Random random = new Random();

    public static Map generateMap(){
        Map newMap = new Map(X, Y);
        List<Tile> way = new ArrayList<Tile>();
        
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                // Filling everything with stones
                newMap.setTile(i, j, TileType.STONE);
            }
        }

        //TODO Generating way properly, at the moment only straight street
        way.add(new Tile(0,Y/2));
        newMap.setTower(0,Y/2, new Base());
        // how many tiles uo or down
        // which direction
        // when to go to the next column
        
        for (int i = 0; i < X; i++) {
			boolean upDown = random.nextBoolean();
			int upDownAmount = random.nextInt(Y);
			
			Tile lastPosition = way.get(way.size()-1);
			
			boolean up = true;
			boolean down = true;
			boolean right = true;
			
			// Setting wether up, down or right are already set, true = empty
			for (Tile t : way) {
				if(t.x == lastPosition.x && Math.abs(t.y - lastPosition.y) == 1) 
					if(t.y - lastPosition.y < 0)
						up = false;
					else
						down = false;
				else if(t.x == lastPosition.x+1 && t.y == lastPosition.y)
					right = false;
			}
			
		}
        
        for (int i = 0; i < X; i++) {
            newMap.setTile(i, Y/2, TileType.GRASS);
        }
        
        //TODO Set the mob path matching with the generated way above
//        newMap.setMobPath(new Path(
//        		new Tile[]{ //Horrible Code
//        				new Tile(15, Y/2), //The tile outside the map where they come from
//        				newMap.getTiles()[14][Y/2],
//        				newMap.getTiles()[13][Y/2],
//        				newMap.getTiles()[12][Y/2],
//        				newMap.getTiles()[11][Y/2],
//        				newMap.getTiles()[10][Y/2],
//        				newMap.getTiles()[9][Y/2],
//        				//testing if corners work properly
//        				newMap.getTiles()[9][Y/2-1],
//        				newMap.getTiles()[8][Y/2-1],
//        				newMap.getTiles()[7][Y/2-1],
//        				newMap.getTiles()[6][Y/2-1],
//        				newMap.getTiles()[5][Y/2-1],
//        				//back to the straight street
//        				newMap.getTiles()[5][Y/2],
//        				newMap.getTiles()[4][Y/2],
//        				newMap.getTiles()[3][Y/2],
//        				newMap.getTiles()[2][Y/2],
//        				newMap.getTiles()[1][Y/2],
//        				newMap.getTiles()[0][Y/2],
//        		}
//        		));
        
        //displaying the tested corner street properly
        
        for(int x = 5; x <= 9; x++)
        	newMap.getTiles()[x][Y/2-1].setType(TileType.GRASS);
        
        for(int x = 6; x <= 8; x++)
        	newMap.getTiles()[x][Y/2].setType(TileType.STONE);
        
        //back to your code ;) (end of corner street)

        //for (int i = 0; i < DIRTAMOUNT; i++) {
        //    newMap.setTile(random.nextInt(X), random.nextInt(Y), TileType.DIRT);
        //}

        return newMap;
    }
    
}