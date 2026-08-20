import java.awt.event.*;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Game  implements Runnable, KeyListener, MouseListener, MouseMotionListener {
    Thread t;
    GameCanvas gameCanvas;
    int speed = 10;

    boolean pressedUP = false;
    boolean pressedDN = false;
    boolean pressedLT = false;
    boolean pressedRT = false;
    boolean pressedK = false;
    boolean pressedN = false;
    boolean pressedE = false;
    boolean mouseClicked = false;

    int mx;
    int my;

    static TileMap map ;
    static TileMap map2 ;
    static TileMap map3 ;
    static TileMap map4 ;
    static TileMap map5 ;
    CollisionMap collisionMap;

    static Player player = new  Player(500,500);
    static ArrayList<Character1> enemies = new ArrayList<>();

    static String state = "Playing";


    public Game(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        this.gameCanvas.requestFocus();
        this.gameCanvas.addKeyListener(this);
        this.gameCanvas.addMouseListener(this);
        this.gameCanvas.addMouseMotionListener(this);

        Tile.loadData();
        Enemies.load();
        enemies= Enemies.enemies;
        map = new TileMap("./misc/ground_map",8,8);
        map2 = new TileMap("./misc/tree_map",128,128);
        map3 = new TileMap("./misc/house_map",320,320);
        map4 = new TileMap("./misc/folliage_map",320,320);
        map5 = new TileMap("./misc/props_map",320,320);
        collisionMap = new CollisionMap(new TileMap[]{map2,map3,map5});
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        //This is the game loop
        while(true) {

            if(pressedN) {
                if (state.equals("Looting")) state = "Playing";

                else if (state.equals("Playing")) state = "Looting";
                try {
                    Thread.sleep(300);// sleeps for a second
                } catch (Exception e) {}
            }
            //this code will execute 60 times a second
           if( state.equals("Playing")) {

               if (!player.alive) {
                   Game.state = "Player Dead";
               }

               //find how the objects in the game will move
               if (player.action != Character1.DEAD) {
                   if (pressedUP) {
                       player.moveUp();
                   } else if (pressedDN) {
                       player.moveDown();
                   } else if (pressedLT) {
                       player.moveLeft();
                   } else if (pressedRT) {
                       player.moveRight();
                   } else {
                       if (!player.isAttacking()) {

                           player.action = player.IDLE;
                       }
                   }

                   if (pressedK) {

                       player.attack();
                   }

               }
               player.update();
               // collision detection

               // checking if the player or enemies are colliding with any of the in game object
               for (int i = 0; i < CollisionMap.rects.size(); i++) {
                   Rect currRect = CollisionMap.rects.get(i);
                   if (player.collidesWith(currRect)) {
                       currRect.pushes(player.hitbox);
                   }

                   for (int enemy = 0; enemy < enemies.size(); enemy++) {
                       Character1 currEnemy = enemies.get(enemy);
                       if (currEnemy.collidesWith(currRect)) {
                           currRect.pushes(currEnemy.hitbox);
                       }
                   }
               }

               // checking if the enemies are  colliding with each other of the players attack box

               for (int i = 0; i < enemies.size(); i++) {
                   Character1 enemy = enemies.get(i);
                   enemy.update();

                   if (player.dealingDamage) {
                       if (player.getDamageBox().overlaps(enemy.hitbox)) {
                           enemy.takeDamage(player.damage);
                           enemy.knockBack(player);
                           if (enemy.droppedLoot) {
                               Clue.addClue();
                               if (Clue.clues.size() ==1 ) Game.state = "Looting";
                           }
                       }
                   }
                   if (enemy.dealingDamage) {
                       if (enemy.getDamageBox().overlaps(player.hitbox)) {
                           player.takeDamage(enemy.damage);
                           player.knockBack(enemy);
                       }
                   }
                   for (int j = i + 1; j < enemies.size(); j++) {  // j = i+1 avoids duplicates
                       Character1 a = enemies.get(i);
                       Character1 b = enemies.get(j);
                       if (a.collidesWith(b.hitbox)) {
                           a.hitbox.pushes(b.hitbox);
                           b.hitbox.pushes(a.hitbox);  // push both ways so neither phases through
                       }
                   }
               }

               GameCanvas.chest.in_player_focus = false;
               if(GameCanvas.chest.overlaps(player.getDamageBox())) {
               GameCanvas.chest.in_player_focus = true;
               if (pressedE) {
                   Game.state = "End";
               }
               }


               Camera.x = Math.max(0, player.x - GameCanvas.WIDTH / 2);
               Camera.y = Math.max(0, player.y - GameCanvas.HEIGHT / 2);
           }
            gameCanvas.repaint();//tells OS window needs to be painted (this is the lowest priority item)

            try {
                Thread.sleep(16);// sleeps for a 60th of a second
            } catch (Exception e) {}
        }
    }

    public void reset(){
        Enemies.load();
        Clue.reset();
        enemies = Enemies.enemies;
        player = new Player(500,500);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Moving objects here will by cause sync issues as it would bypass the gameloop
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) pressedUP = true;
        if (code == KeyEvent.VK_DOWN)pressedDN = true;
        if (code == KeyEvent.VK_LEFT)pressedLT = true;
        if (code == KeyEvent.VK_RIGHT) pressedRT = true;
        if (code == KeyEvent.VK_K)pressedK = true;
        if (code == KeyEvent.VK_N)pressedN = true;
        if (code == KeyEvent.VK_E)pressedE = true;


    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) pressedUP = false;
        if (code == KeyEvent.VK_DOWN) pressedDN = false;
        if (code == KeyEvent.VK_LEFT) pressedLT = false;
        if (code == KeyEvent.VK_RIGHT) pressedRT = false;
        if (code == KeyEvent.VK_K)pressedK = false;
        if (code == KeyEvent.VK_N)pressedN = false;
        if (code == KeyEvent.VK_E)pressedE = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }


        @Override
        public void mousePressed(MouseEvent e) {

            if (Game.state.equals("Player Dead")) {
                if (RespawnMenu.respawn.contains(e.getX(), e.getY())) {
                    this.reset();
                    Game.state = "Playing";
                } else if (RespawnMenu.quit.contains(e.getX(), e.getY())) {
                    System.exit(0);
                }
            }

            if(state.equals("Looting")) {

                    if (Clue.nextPage.contains(mx, my)) {
                        Clue.nextPage();
                        System.out.println("next clicked, page: " + Clue.currentPage + " size: " + Clue.clues.size());
                    }
                    if (Clue.prevPage.contains(mx, my)) {
                        Clue.prevPage();
                    }
                    if (Clue.close.contains(mx, my)) {
                        Game.state = "Playing";
                    }
            }

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            this.mouseClicked = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx = e.getX();
            my = e.getY();
        }

    public static void main(String[] args) {
       GameCanvas gameCanvas = new GameCanvas();
       gameCanvas.requestFocus();

       Game game = new Game(gameCanvas);

        gameCanvas.maps.add(Game.map);
        gameCanvas.maps.add(Game.map2);
        gameCanvas.maps.add(Game.map3);
        gameCanvas.maps.add(Game.map4);
        gameCanvas.maps.add(Game.map5);

    }

}