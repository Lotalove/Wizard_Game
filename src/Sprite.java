import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite extends Rect{

Animation[] animation;
int action;

public Sprite(int x, int y,int width, int height) {
    super(x, y, width, height);
}

//public Image getImage(){
//    Toolkit tk = Toolkit.getDefaultToolkit().cre;
//}

    @Override
public void draw(Graphics g) {
    System.out.println("Drawing Action: " + action);

    if(action == 2){
        g.drawImage(this.animation[0].stillImage(), this.x, this.y,null);
    }
    else{
        g.drawImage(this.animation[this.action].nextImage(), this.x, this.y,null);
    }

}

}
