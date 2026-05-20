public class Vampire extends Character1 {
    static final int SPRITE_WIDTH=64;
    static final int SPRITE_HEIGHT = 64;
    static final double SCALE = 2;

    static final int widthOffset = 22;
    static final int heightOffset =18;
    static final int xOffset =(int) (22 * SCALE);
    static final int yOffset= (int) (18 * SCALE);

    static final int sightRadius=800;
    static final int attackRadius=64;

    public Vampire(int x, int y) {
        super(x, y, SPRITE_WIDTH, SPRITE_HEIGHT, SCALE, 100, attackRadius, xOffset, yOffset, widthOffset, heightOffset);
        loadAnimations();
        this.computer_controlled = true;
        this.ai_sight_range = sightRadius;
        this.speed = 5;
        this.action = IDLE;
    }
    @Override
    protected void loadAnimations() {
        Animation RunUp = new Animation("./art/Vampires1/Walk/walk_up.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation RunDown = new Animation("./art/Vampires1/Walk/walk_down.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation RunRight = new Animation("./art/Vampires1/Walk/walk_right.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation RunLeft = new Animation("./art/Vampires1/Walk/walk_left.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8);

        Animation AttackUp =    new Animation(   "./art/Vampires1/Attack/attack_up.png",12,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation AttackDown =  new Animation( "./art/Vampires1/Attack/attack_down.png",12,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation AttackLeft =  new Animation( "./art/Vampires1/Attack/attack_left.png",12,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation AttackRight = new Animation("./art/Vampires1/Attack/attack_right.png",12,SPRITE_WIDTH,SPRITE_HEIGHT,8);

        Animation IdleUP =    new Animation(   "./art/Vampires1/Idle/idle_up.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation IdleDown =  new Animation( "./art/Vampires1/Idle/idle_down.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation IdleLeft =  new Animation( "./art/Vampires1/Idle/idle_left.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation IdleRight = new Animation("./art/Vampires1/Idle/idle_right.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8);


        Animation HurtUp =    new Animation(   "./art/Vampires1/Hurt/hurt_up.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation HurtDown =  new Animation( "./art/Vampires1/Hurt/hurt_down.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation HurtLeft =  new Animation( "./art/Vampires1/Hurt/hurt_left.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation HurtRight = new Animation("./art/Vampires1/Hurt/hurt_right.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8);

        Animation DeadUp =    new Animation(   "./art/Vampires1/Death/death_up.png",11,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation DeadDown =  new Animation( "./art/Vampires1/Death/death_down.png",11,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation DeadLeft =  new Animation( "./art/Vampires1/Death/death_left.png",11,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation DeadRight = new Animation("./art/Vampires1/Death/death_right.png",11,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);


        Animation[] run = new Animation[] {RunUp, RunDown, RunLeft, RunRight};
        Animation[] attack = new Animation[] {AttackUp,AttackDown,AttackLeft,AttackRight};
        Animation[] idle = new Animation[] {IdleUP,IdleDown,IdleLeft,IdleRight};
        Animation[] hurt = new Animation[]{HurtUp,HurtDown,HurtLeft,HurtRight};
        Animation[] death = new Animation[]{DeadUp,DeadDown,DeadLeft,DeadRight};
        this.animations = new Animation[][] {idle, run,attack,hurt,death};
        this.action = IDLE;
    }
}
