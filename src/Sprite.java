import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
int frameWidth;
int frameHeight;
int maxFrames;
int x;
int y;
BufferedImage image;
String path;
float currentFrame=0;

public Sprite(String pathToImage,int width, int height, int x, int y,int maxFrames) {
    this.path=pathToImage;
    this.frameWidth = width;
    this.frameHeight = height;
    this.x = x;
    this.y = y;
    this.maxFrames = maxFrames;
    loadImage();
}

public void loadImage(){
    try {
        this.image = ImageIO.read(new File(this.path));
    } catch (IOException e) {
        //throw new IOException("Failed to load image from " + this.path);
    }
}
public void draw(Graphics g) {

    int sx1 = Math.round(currentFrame) * frameWidth;
    int sy1 = 0; // Assuming first row
    int sx2 = sx1 + frameWidth;
    int sy2 = sy1 + frameHeight;
    /*
    * sx1 – the x coordinate of the first corner of the source rectangle.
sy1 – the y coordinate of the first corner of the source rectangle.
sx2 – the x coordinate of the second corner of the source rectangle.
sy2 – the y coordinate of the second corner of the source rectangle.
    * */
    g.drawImage(image, x, y, x + frameWidth, y+ frameHeight, sx1, sy1, sx2, sy2, null);

}

}
