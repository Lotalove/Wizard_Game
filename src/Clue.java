import javax.xml.soap.Text;
import java.awt.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clue {
    static Image paper = Toolkit.getDefaultToolkit().getImage("misc/loose_leaf.png");

    static Button close =    new Button(100, 100, 120, 50, "Close");
    static Button prevPage = new Button(50, GameCanvas.HEIGHT - 80, 120, 50, "Prev Page");
    static Button nextPage = new Button(GameCanvas.WIDTH - 170, GameCanvas.HEIGHT - 80, 120, 50, "Next Page");

    static List<String> clues = new ArrayList<>();
    static int currentPage = 0;

    static final int MARGIN_X = 80;
    static final int MARGIN_TOP = 80;
    static final int LINE_HEIGHT = 160;

    public Clue() {}

    public static void addClue() {
        // clues are in the format:
        // x/y space number_slot(1's, 10's, 100's, 1000's) space digit

        Random random = new Random();

        // Pick x or y randomly
        String[] cordOptions = new String[]{"x", "y"};
        String cordType = cordOptions[random.nextInt(cordOptions.length)];


        int cordValue = cordType.equals("x") ? GameCanvas.chest.x : GameCanvas.chest.y;
        String[] digits = Integer.toString(cordValue).split("");


        int idx = random.nextInt(digits.length);


        String maskedCoord = "";
        for (int i = 0; i < digits.length; i++) {
            if (i != idx) {
                maskedCoord = maskedCoord.concat("?");
            } else {
                maskedCoord = maskedCoord.concat(digits[i]);
            }
        }

        String text = cordType + ": " + maskedCoord;
        clues.add(text);
        currentPage = clues.size() - 1; //
    }


    static public void draw(Graphics g) {
        // Draw paper texture
        g.drawImage(paper, 0, 0, GameCanvas.WIDTH, GameCanvas.HEIGHT, null);

        // Draw text only

        g.setFont(new Font("Courier New", Font.PLAIN, 64));
        g.setColor(Color.BLACK);


                int y = MARGIN_TOP +  LINE_HEIGHT;
                if(clues.isEmpty())g.drawString("No clues have been found", MARGIN_X + 10, y - 10);
                else g.drawString(clues.get(currentPage), MARGIN_X + 10, y - 10);
        ;
        g.drawString(currentPage+1 +"/" + (clues.size()), GameCanvas.WIDTH/2 -128, GameCanvas.HEIGHT - 128);
        prevPage.y = GameCanvas.HEIGHT - 80;
        nextPage.x= GameCanvas.WIDTH - 170;
        nextPage.y = GameCanvas.HEIGHT - 80;

        // Draw buttons
        g.setFont(new Font("Courier New", Font.PLAIN, 24));
        close.draw(g);
        prevPage.draw(g);
        nextPage.draw(g);
    }

    public static void nextPage() {
        currentPage = Math.min(clues.size()-1,currentPage + 1);
    }

    public static void prevPage() {
        if (currentPage > 0) currentPage--;
    }
}