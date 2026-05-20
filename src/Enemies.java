import java.io.*;
import java.util.ArrayList;

public class Enemies {
    public static ArrayList<Character1> enemies ;

    public static void load(){

        enemies = new ArrayList<Character1>();

        String filename = "./misc/enemy_locations";
        File file = new File(filename);

        try
        {
            BufferedReader input = new BufferedReader(new FileReader(file));

            int n = Integer.parseInt(input.readLine());

            for(int i = 0; i < n; i++)
            {
                String[] enemy_information = input.readLine().split(" ");
                String type = enemy_information[0];
                int x = Integer.parseInt(enemy_information[1]);
                int y = Integer.parseInt(enemy_information[2]);

                Character1 newCharacter;
                if(type.equals("Soldier")){
                    newCharacter = new Soldier(x, y);
                }
                else if(type.equals("Vampire")){
                newCharacter = new Vampire(x, y);
            }
                else {
                    newCharacter = new Soldier(x, y);
                }
                enemies.add(newCharacter);
            }

            input.close();
        }
        catch(IOException x) {};

    }
}
