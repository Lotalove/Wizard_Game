import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Game  implements Runnable, KeyListener {
    Thread t;
    GameCanvas gameCanvas;
    static Sprite bear = new Sprite("art/bear_walk.png",128,128,0,0,5);
    static  Sprite wizard = new Sprite("art/Wizard/run.png",231,190,0,0,8);
    boolean pressedUP = false;
    boolean pressedDN = false;
    boolean pressedLT = false;
    boolean pressedRT = false;
    Player player;

    public Game(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        this.gameCanvas.requestFocus();
        this.gameCanvas.addKeyListener(this);
        this.player =  player = new Player(0,0,wizard);
        t = new Thread(this);
        t.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Moving objects here will by cause sync issues as it would bypass the gameloop
        int code = e.getKeyCode();
        System.out.println(code);
        if (code == KeyEvent.VK_UP) pressedUP = true;
        if (code == KeyEvent.VK_DOWN)pressedDN = true;
        if (code == KeyEvent.VK_LEFT)pressedLT = true;
        if (code == KeyEvent.VK_RIGHT) pressedRT = true;


    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) pressedUP = false;
        if (code == KeyEvent.VK_DOWN) pressedDN = false;
        if (code == KeyEvent.VK_LEFT) pressedLT = false;
        if (code == KeyEvent.VK_RIGHT) pressedRT = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void run() {
        //This is the game loop
        while(true) {
            //this code will execute 60 times a second
            if (pressedUP) {
                player.action = Player.Action.WALK;
                player.moveBy(0,-3);
            }
            else if (pressedDN) {
                player.action = Player.Action.WALK;
                player.moveBy(0,3);
            }
            else if (pressedLT) {
                player.action = Player.Action.WALK;
                player.moveBy(-3,0);
            }
            else if (pressedRT) {
                player.action = Player.Action.WALK;
                player.moveBy(3,0);
            }
            else {
                player.action = Player.Action.IDLE;
                player.resetFrame();
            }

            if (player.hitbox.overlaps(gameCanvas.enemies.get(0))){
                System.out.println("Player hit");
            }



            //find how the objects in the game will move
            // collision detection
            gameCanvas.repaint();//tells OS window needs to be painted (this is the lowest priority item)


            try {
                Thread.sleep(16);// sleeps for a 60th of a second
            } catch (Exception e) {}
        }
    }
    public static void main(String[] args) {
       GameCanvas gameCanvas = new GameCanvas();
       Game game = new Game(gameCanvas);
       Rect enemy = new Rect(100,100,50,50);
       gameCanvas.addToScene(game.player);
       gameCanvas.addToScene(enemy);
    }
}