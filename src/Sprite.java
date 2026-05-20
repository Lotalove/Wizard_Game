import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite extends Rect {

    Animation[][] animations;
    int action;
    int direction;

    int UP = 0;
    int DOWN = 1;
    int LEFT = 2;
    int RIGHT = 3;

    int originalWidth;
    int originalHeight;
    boolean inverted = false;
    double scale = 1.0;

    boolean visible = true;
    Rect hitbox;
    ;
    protected boolean receivingDamage = false;

    public Sprite(int x, int y, int width, int height, double scale) {
        super(x, y, (int) (width * scale), (int) (height * scale));
        this.scale = scale;
        this.originalWidth = width;   // Store the unscaled size
        this.originalHeight = height; // Store the unscaled size
        this.hitbox = new Rect(x, y, (int) (width * scale), (int) (height * scale));
    }


    @Override
    public void draw(Graphics g) {
        if (!visible) {return;}

        Image img = this.animations[this.action][direction].nextImage();

        g.drawImage(img, x - Camera.x, y - Camera.y, width, height, null);

    }
}