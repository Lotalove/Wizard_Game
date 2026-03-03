import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animation
{
    BufferedImage[] images;

    int numFrames;
    int frameWidth;
    int frameHeight;

    int current = 0;

    int duration;
    int delay;

    public Animation(String path, int numFrames, int frameWidth, int frameHeight, int duration)
    {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.duration = duration;
        delay = duration;
        this.numFrames = numFrames;
        images = new BufferedImage[numFrames];
        loadImage(path);

    }

    public void loadImage(String path){
        System.out.println("Frame width: " + frameWidth + " Frame height: " + frameHeight);
        try {
            BufferedImage image = ImageIO.read(new File(path));

            for(int i = 0; i < this.numFrames; i++) {
                // Keep Y at 0, only move X
                this.images[i] = image.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
            }

        } catch (IOException e) {
            //throw new IOException("Failed to load image from " + this.path);
            e.printStackTrace(); // This will tell you if the file actually exists
        }


    }

    public Image nextImage()
    {
        if(delay == 0)
        {
            current++;

            if(current >= images.length)  current = 1;

            delay = duration;
        }
        else
        {
            delay--;
        }

        return images[current];
    }

    public Image stillImage()
    {
        return images[0];
    }


}