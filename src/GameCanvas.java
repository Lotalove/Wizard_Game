import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.applet.*;
import java.util.ArrayList;

@SuppressWarnings("removal")
public class GameCanvas extends JPanel {
    ArrayList<Player> sprites = new ArrayList<Player>();
    ArrayList<Rect> enemies = new ArrayList<Rect>();
    int WIDTH= 1920;
    int HEIGHT = 1080;

    int gridSize = 64;

    int [][] map = new int[WIDTH/gridSize][HEIGHT/gridSize];
    public GameCanvas() {
        JFrame frame = new JFrame("Game Screen");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add our custom drawing panel to the frame
        frame.add(this);
        frame.setVisible(true);
    }

    public void addToScene(Player s){
        sprites.add(s);
    }
    public void addToScene(Rect r){
        enemies.add(r);
    }
    @Override
    protected void paintComponent(Graphics g) {
        // 1. Always call the super method first to clear the screen
        super.paintComponent(g);

        for(int row=0;row<map.length;row++){
            for(int col=0;col<map[row].length;col++){
                g.setColor(Color.GREEN);
                g.fillRect(col*gridSize,row*gridSize,gridSize,gridSize);
                g.setColor(Color.BLACK);
                g.drawRect(col*gridSize,row*gridSize,gridSize,gridSize);
            }
        }
        try {
            for (Player s : sprites) {
                s.draw(g);
            }
            for (Rect e : enemies) {
                e.draw(g);
            }
        }
        catch (Exception e) {}
    }



}
