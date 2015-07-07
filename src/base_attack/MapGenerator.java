package base_attack;

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
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                // Filling everything with stones
                newMap.setTile(i, j, TileType.STONE);
            }
        }

        //TODO Generating way properly, at the moment only straight street
        newMap.setTower(0, (Y/2), new Base());

        for (int i = 0; i < X; i++) {
            newMap.setTile(i, Y/2, TileType.GRASS);
        }

        //for (int i = 0; i < DIRTAMOUNT; i++) {
        //    newMap.setTile(random.nextInt(X), random.nextInt(Y), TileType.DIRT);
        //}

        return newMap;
    }
    
}