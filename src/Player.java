public class Player extends Character1 {
    static final int SPRITE_WIDTH = 64;
    static final int SPRITE_HEIGHT = 64;
    static final double SCALE = 1.2;

    static final int widthOffset =32;
    static final int heightOffset = 22;
    static final int xOffset =24;
    static final int yOffset =32;

    static final int attackRadius=32;

    static int maxHealth = 100;

    public Player(int x, int y) {
        super(x, y, SPRITE_WIDTH, SPRITE_HEIGHT, SCALE, maxHealth, attackRadius, xOffset, yOffset, widthOffset, heightOffset);
        loadAnimations();
        this.action = IDLE;
        this.speed = 6;
        this.computer_controlled = false;
    }

    @Override
    protected void loadAnimations() {
        Animation wizardBackWalk = new Animation( "./art/Wizard_3/walk/back_walk.png",9,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation wizardFrontWalk = new Animation("./art/Wizard_3/walk/front_walk.png",9,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation wizardLeftWalk = new Animation( "./art/Wizard_3/walk/left_walk.png",9,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation wizardRightWalk = new Animation("./art/Wizard_3/walk/right_walk.png",9,SPRITE_WIDTH,SPRITE_HEIGHT,8);

        Animation wizardBackAttack = new Animation(   "./art/Wizard_3/thrust/thrust_back.png",8,SPRITE_WIDTH,SPRITE_HEIGHT,4,false, new int[] {5});
        Animation wizardFrontAttack = new Animation(   "./art/Wizard_3/thrust/thrust_front.png",8,SPRITE_WIDTH,SPRITE_HEIGHT,4,false,new int[] {5});
        Animation wizardLeftAttack = new Animation(   "./art/Wizard_3/thrust/thrust_left.png",8,SPRITE_WIDTH,SPRITE_HEIGHT,4,false,new int[] {5});
        Animation wizardRightAttack = new Animation(   "./art/Wizard_3/thrust/thrust_right.png",8,SPRITE_WIDTH,SPRITE_HEIGHT,4,false,new int[] {5});

        Animation wizardBackIdle =  new Animation( "./art/Wizard_3/idle/idle_back.png",2,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation wizardFrontIdle = new Animation("./art/Wizard_3/idle/idle_front.png",2,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation wizardLeftIdle =  new Animation( "./art/Wizard_3/idle/idle_left.png",2,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation wizardRightIdle = new Animation("./art/Wizard_3/idle/idle_right.png",2,SPRITE_WIDTH,SPRITE_HEIGHT,8);

        Animation HurtUp =    new Animation( "./art/Wizard_3/hurt/hurt_back.png",2,SPRITE_WIDTH,SPRITE_HEIGHT,16);
        Animation HurtDown =  new Animation("./art/Wizard_3/hurt/hurt_front.png",2,SPRITE_WIDTH,SPRITE_HEIGHT,16);
        Animation HurtLeft =  new Animation( "./art/Wizard_3/hurt/hurt_left.png",2,SPRITE_WIDTH,SPRITE_HEIGHT,16);
        Animation HurtRight = new Animation("./art/Wizard_3/hurt/hurt_right.png",2,SPRITE_WIDTH,SPRITE_HEIGHT,16);

        Animation DeadBack =  new Animation("./art/Wizard_3/dead.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation DeadFront =  new Animation("./art/Wizard_3/dead.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation DeadLeft =  new Animation("./art/Wizard_3/dead.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation DeadRight =  new Animation("./art/Wizard_3/dead.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);


        Animation[] walking = new Animation[] {wizardBackWalk,wizardFrontWalk,wizardLeftWalk,wizardRightWalk};
        Animation[] idle = new Animation[] {wizardBackIdle,wizardFrontIdle,wizardLeftIdle,wizardRightIdle};
        Animation[] attacks = new Animation[] {wizardBackAttack,wizardFrontAttack,wizardLeftAttack,wizardRightAttack};
        Animation[] hurt = new Animation[]{HurtUp,HurtDown,HurtLeft,HurtRight};
        Animation[] death = new Animation[]{DeadBack,DeadFront,DeadLeft,DeadRight};
        this.animations = new Animation[][] {idle,walking,attacks,hurt,death};
    }
}
