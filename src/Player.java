import java.awt.*;

public class Player {
    int x ;
    int y;
    Sprite sprite;
    Action action;
    Rect hitbox;
    int health = 100;
    enum Action {
        IDLE,
        WALK,
        RUN,
        JUMP
    }

    public Player(int x, int y, Sprite sprite) {

        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.action = Action.IDLE;
        this.hitbox = new Rect(this.x+100, this.y+64 , 16, 64);


    }

    public void moveBy(int x, int y){
        this.x +=x;
        this.y +=y;
        this.sprite.x = this.x;
        this.sprite.y = this.y;
        this.hitbox.x = this.x+100;
        this.hitbox.y = this.y+64;
    }
    public void draw(Graphics g) {
        if(this.action == Action.WALK) {
            System.out.println("Player is walking");
            if (sprite.currentFrame < sprite.maxFrames) {
                sprite.currentFrame += .2;
            } else {
                sprite.currentFrame = 0;
            }
        }
        hitbox.draw(g);
        //g.drawRect(this.x+100, this.y+64 , 16, 64); // this is the players hitbox
        sprite.draw(g);

    }

    public void resetFrame(){
        this.sprite.currentFrame = 0;
    }
}
