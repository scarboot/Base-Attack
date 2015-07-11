package base_attack;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Yannick on 07.07.2015.
 * Project: Base-Attack
 */
public class MapGenerator {

    public static final int X = 15, Y = 9, DIRTAMOUNT = 3; //DIRTAMAOUNT IN REVERSE PERCENT
    public static Random random = new Random();
    //Lukas: Added parameter 'game' for initiating the Base properly
    public static Map generateMap(Game game){
        Map newMap = new Map(X, Y);
        List<Tile> way = new ArrayList<>();

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                // Filling everything with stones
                newMap.setTile(i, j, TileType.STONE);
            }
        }

        way.add(new Tile(0,Y/2));
        newMap.setTower(0,Y/2, new Base(game, newMap.getTiles()[0][Y/2]));
        // how many tiles uo or down
        // which direction
        // when to go to the next column

        int dirtGroupBorder = 0;
        while(true) {

			Tile lastPosition = way.get(way.size()-1);

            Tile[] possibleTiles = {
                    new Tile(lastPosition.x+2, lastPosition.y), //right
                    new Tile(lastPosition.x, lastPosition.y+1), //down
                    new Tile(lastPosition.x, lastPosition.y-1)  //up
            };

            int chosenTile = random.nextInt(possibleTiles.length);
            Tile nextTile = possibleTiles[chosenTile];

            //if end is reached: add last tile and exit, has to be at this position
            if(nextTile.x == X - 1){
                newMap.setTile(lastPosition.x+1, nextTile.y, TileType.GRASS);
                way.add(new Tile(lastPosition.x+1, nextTile.y));
                newMap.setTile(lastPosition.x+2, nextTile.y, TileType.GRASS);
                way.add(new Tile(lastPosition.x+2, nextTile.y));
                break;
            }

            // checking for walls (may be earlier)
            if(nextTile.y == Y - 1){
                if(random.nextBoolean()) {
                    chosenTile = 0;
                    nextTile = possibleTiles[0];
                }
                else {
                    chosenTile = 2;
                    nextTile = possibleTiles[2];
                }
            }

            if(Math.abs(Y - nextTile.y) < 2) {
                if(random.nextInt(nextTile.y) <= Y/2) {
                    if(random.nextBoolean()) {
                        chosenTile = 0;
                        nextTile = possibleTiles[0];
                    }
                    else {
                        chosenTile = 2;
                        nextTile = possibleTiles[2];
                    }
                }
            }

            // Setting of the tiles between des 2nth next tile (see plus 2)
            if(chosenTile == 0) {
                newMap.setTile(nextTile.x-1, nextTile.y, TileType.GRASS);
                way.add(new Tile(nextTile.x-1, nextTile.y));
            }

            if(nextTile.y == -1 || nextTile.x == -1)
                continue;

            boolean alreadyUsed = newMap.getTiles()[nextTile.x][nextTile.y].getType() == TileType.GRASS || newMap.getTiles()[nextTile.x][nextTile.y].getType() == TileType.DIRT;
            boolean hasTower = newMap.getTiles()[nextTile.x][nextTile.y].hasTower();

            // checking whether tile is free
            if(alreadyUsed || hasTower)
                continue;

            // adding new tile with  the certain probability of dirt
            TileType type;
            if(random.nextInt(100) > 90 && dirtGroupBorder == 0) {
                dirtGroupBorder = DIRTAMOUNT;
            }
            if(dirtGroupBorder > 0) {
                type = TileType.DIRT;
                dirtGroupBorder--;
            }
            else {
                type = TileType.GRASS;
            }
            newMap.setTile(nextTile.x, nextTile.y, type);
            way.add(new Tile(nextTile.x, nextTile.y));
		}//Lukas: Added TileType.GRASS instead of the default STONE and changed X+1 to X
        way.add(new Tile(TileType.GRASS, X, way.get(way.size()-1).y));
        newMap.setMobPath(Path.createReversedPath(way));
        return newMap;
    }

        //for (int i = 0; i < X; i++) {
        //    newMap.setTile(i, Y/2, TileType.GRASS);
        //}
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

        //for(int x = 5; x <= 9; x++)
        //	newMap.getTiles()[x][Y/2-1].setType(TileType.GRASS);

        //for(int x = 6; x <= 8; x++)
        //	newMap.getTiles()[x][Y/2].setType(TileType.STONE);

        //back to your code ;) (end of corner street)

        //for (int i = 0; i < DIRTAMOUNT; i++) {
        //    newMap.setTile(random.nextInt(X), random.nextInt(Y), TileType.DIRT);
        //}
    
}