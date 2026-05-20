import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.util.ArrayList;

@SuppressWarnings("removal")
public class GameCanvas extends JPanel {
    private BufferedImage dbImage;
    private Graphics dbGraphics;
    static Chest chest = new Chest();
    ArrayList<TileMap> maps = new ArrayList<TileMap>();


    static int WIDTH= 1920;
    static int HEIGHT = 1080;


    HUD hud = new HUD();


    public GameCanvas() {
        JFrame frame = new JFrame("Wizard Game");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add our custom drawing panel to the frame
        frame.add(this);
        frame.setVisible(true);
    }



    @Override
    protected void paintComponent(Graphics g) {

        Player player = Game.player;
        // 1. Always call the super method first to clear the screen
        WIDTH = getWidth();
        HEIGHT = getHeight();
        super.paintComponent(g);

        if(Game.state.equals("Player Dead")){
            RespawnMenu.draw(g);
            return;
        }
        for (TileMap m : maps) {
            m.draw(g);
        }



        player.draw(g);


        for (Character1 e : Game.enemies) {
            e.draw(g);
            if (e.visible){
                e.drawHealthBar(g);
            }
        }
        hud.drawHealthBar(g);
        chest.draw(g);
        if(Game.state.equals("Looting")){
           Clue.draw(g);
        }
        if(Game.state.equals("End")){
            EndGameScreen.draw(g);
        }
    }


    }


