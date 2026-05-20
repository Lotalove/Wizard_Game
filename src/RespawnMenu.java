import java.awt.*;

public class RespawnMenu {

    static Button respawn = new Button(
            GameCanvas.WIDTH / 2 - 100,
            GameCanvas.HEIGHT / 2 - 25,
            200, 50, "Respawn"
    );

    static Button quit = new Button(
            GameCanvas.WIDTH / 2 - 100,
            GameCanvas.HEIGHT / 2 + 50,
            200, 50, "Quit"
    );

    public static void draw(Graphics g) {
        // Dark overlay over the game world
        g.setColor(new Color(0, 0, 0, 160));
        g.fillRect(0, 0, GameCanvas.WIDTH, GameCanvas.HEIGHT);

        // YOU DIED title
        g.setFont(new Font("Georgia", Font.BOLD, 48));
        FontMetrics fm = g.getFontMetrics();
        String title = "YOU DIED";
        int titleX = GameCanvas.WIDTH / 2 - fm.stringWidth(title) / 2;
        g.setColor(new Color(180, 60, 60));
        g.drawString(title, titleX, GameCanvas.HEIGHT / 2 - 80);

        // Buttons
        respawn.draw(g);
        quit.draw(g);
    }

//    public static void handleClick(int mouseX, int mouseY) {
//        if (respawn.contains(mouseX, mouseY)) {
//            Game.player.respawn();
//            Game.state = "playing";
//        }
//        if (quit.contains(mouseX, mouseY)) {
//            System.exit(0);
//        }
//    }
}