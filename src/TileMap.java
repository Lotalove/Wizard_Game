import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class TileMap {
BufferedImage mapCache;
int [] [] grid;
int w,h;
int tw,th;
public static int gridWidth = 8;

String filename;

public TileMap(String filename,int tw, int th) {
    this.filename = filename;
    this.tw = tw;
    this.th = th;

    loadMap(filename);
    //preRenderMap();
}

    public void loadMap(String filename)
    {
        File file = new File(filename);

        try
        {
            BufferedReader input = new BufferedReader(new FileReader(file));

            this.h = Integer.parseInt(input.readLine());
            this.w = Integer.parseInt(input.readLine());

            mapCache = new BufferedImage(this.w * TileMap.gridWidth, this.h * TileMap.gridWidth, BufferedImage.TYPE_INT_ARGB);
            Graphics g = mapCache.getGraphics();

            grid = new int[this.h][this.w];

            for(int row = 0; row < h; row++)
            {
                String[] rowString = input.readLine().split(" ");
                int[] newRow = new int[rowString.length];
                for(int col = 0; col < w; col++){
                    newRow[col] = Integer.parseInt(rowString[col]);
                    if (newRow[col] != 0) {  // skip if no tile assigned
                        Tile tile = Tile.tiles.get(newRow[col]-1);
                        g.drawImage(tile.image, col *TileMap.gridWidth,row * TileMap.gridWidth - tile.height, null);
                    }
                }
                grid[row] = newRow;
            }
            g.dispose();
            input.close();
        }
        catch(IOException x) {};

    }


public void draw(Graphics g){
    // figure out which part of the map is visible
    int srcX = Camera.x;  // top-left of visible area in map coordinates
    int srcY = Camera.y;

    g.drawImage(
            mapCache,
            0,0, GameCanvas.WIDTH, GameCanvas.HEIGHT,                    // destination (full screen)
            srcX, srcY, srcX + GameCanvas.WIDTH, srcY + GameCanvas.HEIGHT, // source (camera slice)
            null
    );
}

}
