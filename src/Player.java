import java.awt.*;

public class Player extends Sprite {

    final static int width = 231;
    final static int height = 190;


    int health = 100;
    int spriteIDX = 0;
    float attackFrame = -1; // -1 means the player is not attacking
    Animation wizardRun = new Animation("./art/Wizard/run.png",8,width,height,8);
    Animation wizardAttack = new Animation("./art/Wizard/Attack1.png",8,width,height,8);



    int RUNNING = 0;
    int ATTACKING = 1;
    int IDLE = 2;

    enum Action {
        IDLE,
        WALK,
        RUN,
        JUMP,
        Attack
    }

    public Player(int x, int y) {
        super(x,y,width,height);
        this.animation = new Animation[] {wizardRun, wizardAttack};
        this.action = IDLE;
    }



    public void moveUp(int dy){
    this.y -= dy;
    this.action = RUNNING;
    }
    public void moveDown(int dy){
        this.y += dy;
        this.action = RUNNING;
    }

    public void moveLeft(int dx){
        this.x-=dx;
        this.action = RUNNING;
    }

    public void moveRight(int dx){
        this.x+=dx;
        this.action = RUNNING;
    }
    public void moveBy(int x, int y){
        this.x +=x;
        this.y +=y;

    }
    public void attack(){
        System.out.println("attacking");
        this.attackFrame = 0;
    }
    public boolean isAttacking(){
        return this.attackFrame > -1 && this.attackFrame <=7;
    }


//    public void resetFrame(){
//        this.sprites[spriteIDX].currentFrame = 0;
//    }
}
