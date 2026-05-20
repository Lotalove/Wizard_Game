import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Tile {

    int type;
    int width, height;
    Image image;
    static ArrayList<Tile> tiles = new ArrayList<Tile>() ;


    public Tile(int type, int width, int height, String filename) {
        this.type = type;
        this.width = width;
        this.height = height;

        try {
            // Use ImageIO to force synchronous loading
           image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.out.println("Error loading tile images!");
            e.printStackTrace();
        }
        System.out.println("Loading tile images!");
    }

    public static void loadData()
    {
        File file = new File("./misc/tile_data");

        try
        {
            BufferedReader input = new BufferedReader(new FileReader(file));

           int numTiles = Integer.parseInt(input.readLine());

           for (int i = 0; i < numTiles; i++){
               String[] tileData =  input.readLine().split(" ");
               System.out.println(tileData[3]);
               tiles.add(new Tile(
                                    Integer.parseInt(tileData[0]),
                                    Integer.parseInt(tileData[1]),
                                    Integer.parseInt(tileData[2]),
                                    tileData[3]
                                    )) ;

           }

            input.close();
        }
        catch(IOException x) {};

    }
}
