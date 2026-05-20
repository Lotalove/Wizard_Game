import java.awt.*;

public class Button extends Rect {
String text;
    public Button(int x, int y, int width, int height,String text) {
        super(x, y, width, height);
        this.text= text;
    }

    public void draw(Graphics g) {
        // Draw the rectangle
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);

        // Draw border
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        // Center the text
        FontMetrics fm = g.getFontMetrics();
        int textX = x + (width - fm.stringWidth(text)) / 2;
        int textY = y + (height - fm.getHeight()) / 2 + fm.getAscent();

        g.setColor(Color.WHITE);
        g.drawString(text, textX, textY);
    }
}
