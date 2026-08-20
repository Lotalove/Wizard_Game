import com.sun.java.accessibility.util.TopLevelWindowListener;

import java.awt.*;

public class Chest {
    int x;
    int y;
    public static int width = 35;
    public static int height = 25;
    public static String path = "./art/Tiles/Props/chest001.png";

    Image interaction;
    boolean in_player_focus = false;

    Animation anim = new Animation(path, 3, width, height, 8);

    public Chest() {
        int min = 0;
        int max = 1024 * 8; // the width and heght of the map
        this.x = min + (int) (Math.random() * ((max - min) + 1));
        this.y = min + (int) (Math.random() * ((max - min) + 1));
//        this.x = 300;
//        this.y = 300;
        this.interaction = Toolkit.getDefaultToolkit().getImage("misc/Interact.png");
        System.out.println("x:" + x + " y:" + y);

    }

    public boolean overlaps(Rect r) {
        return (x <= r.x + r.width) &&
                (y <= r.y + r.height) &&

                (r.x <= x + width) &&
                (r.y <= y + height);
    }

    public void draw(Graphics g) {
        if (in_player_focus) {
            g.setColor(Color.white);
            g.drawRect(x - Camera.x, y - Camera.y, width, height);
            g.setColor(Color.black);
            g.drawImage(interaction, x - Camera.x - 128, y - Camera.y - 128, 128, 128, null);
        }
        g.drawImage(anim.stillImage(), x - Camera.x, y - Camera.y, null);
    }
}