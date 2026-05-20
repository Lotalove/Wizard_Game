import java.awt.*;

public class EndGameScreen {

    static Image img = Toolkit.getDefaultToolkit().getImage("misc/end_game.png");
    static public void draw(Graphics g){
        g.drawImage(img, 0, 0, GameCanvas.WIDTH,GameCanvas.HEIGHT, null);
    }
}
