import java.util.ArrayList;

public class CollisionMap {
    static ArrayList<Rect> rects = new ArrayList<Rect>();

    int [] [] grid;
    public CollisionMap(TileMap[] tileMaps) {
        getCollisionMap(tileMaps);
    }


    public void getCollisionMap(TileMap[] tileMaps) {
        /**
         * Given the tilemaps for all collidable objects we return a tilemap that represnts the area where player collides
         * with an object
         * */

        int mapRows = tileMaps[0].h;
        int mapCols = tileMaps[0].w;

        grid = new int[mapRows][mapCols];
        for (int row = 0; row < mapRows; row++) {
            for (int col = 0; col < mapCols; col++) {
                for (TileMap tileMap : tileMaps) {
                    if (tileMap.grid[row][col] != 0) {
                        Tile currTile = Tile.tiles.get(tileMap.grid[row][col]-1);
                        rects.add(new Rect(col * 8, row * 8 - currTile.height, currTile.width, currTile.height));
                    }
                }
            }
        }

    }


}
