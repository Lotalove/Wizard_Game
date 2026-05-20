import java.awt.*;

public class HUD {
    Rect healthBar = new Rect(0, 0, 1000, 100);

    public void drawHealthBar(Graphics g) {
        float healthPercent = (float) Game.player.health / Player.maxHealth;

        // 1. Red fill (based on current health)
        g.setColor(Color.RED);
        g.fillRect(healthBar.x, healthBar.y,
                (int) (healthBar.width * healthPercent), healthBar.height);

        // 2. Black outline on top
        g.setColor(Color.BLACK);
        g.drawRect(healthBar.x, healthBar.y, healthBar.width, healthBar.height);

        // 3. Health text centered inside the bar
        String healthText = "Health: " + Game.player.health + "/" + Player.maxHealth;
        g.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fm = g.getFontMetrics();
        int textX = healthBar.x + (healthBar.width - fm.stringWidth(healthText)) / 2;
        int textY = healthBar.y + (healthBar.height - fm.getHeight()) / 2 + fm.getAscent();
        g.setColor(Color.WHITE);
        g.drawString(healthText, textX, textY);

        String cordText= "X: " + Game.player.x + " Y: " + Game.player.y;
        g.setFont(new Font("Arial", Font.BOLD, 24));

        g.setColor(Color.WHITE);
        g.drawString(cordText, textX, textY + healthBar.height);

    }
}