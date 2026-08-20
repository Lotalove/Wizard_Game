public class Soldier extends Character1 {
    static final int SPRITE_WIDTH=48;
    static final int SPRITE_HEIGHT = 48;
    static final double SCALE = 2;

    static final int widthOffset =0;
    static final int heightOffset = 0;
    static final int xOffset =(int) (12 * SCALE);
    static final int yOffset =(int) (6 * SCALE);

    static final int sightRadius=800;
    static final int attackRadius=64;
    static final int d=33;
    public Soldier(int x, int y) {
        super(x, y, SPRITE_WIDTH, SPRITE_HEIGHT, SCALE, 100, d,attackRadius, xOffset, yOffset, widthOffset, heightOffset);
        loadAnimations();
        this.computer_controlled = true;
        this.ai_sight_range= sightRadius;
        this.speed = 5;
        this.action = IDLE;
    }

    @Override
    protected void loadAnimations() {
        Animation RunUp = new Animation("./art/Soldier/Up/WarriorUpWalk.png",8,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation RunDown = new Animation("./art/Soldier/Down/WarriorDownWalk.png",8,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation RunRight = new Animation("./art/Soldier/Right/WarriorRightWalk.png",8,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation RunLeft = new Animation("./art/Soldier/Left/WarriorLeftWalk.png",8,SPRITE_WIDTH,SPRITE_HEIGHT,8);

        Animation AttackUp = new Animation(   "./art/Soldier/Up/WarriorUpAttack01.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8, false,new int[]{4});
        Animation AttackDown = new Animation( "./art/Soldier/Down/WarriorDownAttack01.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,new int[]{4});
        Animation AttackLeft = new Animation( "./art/Soldier/Left/WarriorLeftAttack01.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,new int[]{4});
        Animation AttackRight = new Animation("./art/Soldier/Right/WarriorRightAttack01.png",6,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,new int[]{4});

        Animation IdleUP = new Animation("./art/Soldier/Up/WarriorUpIdle.png",5,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation IdleDown = new Animation("./art/Soldier/Down/WarriorDownIdle.png",5,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation IdleLeft = new Animation("./art/Soldier/Left/WarriorLeftIdle.png",5,SPRITE_WIDTH,SPRITE_HEIGHT,8);
        Animation IdleRight = new Animation("./art/Soldier/Right/WarriorRightIdle.png",5,SPRITE_WIDTH,SPRITE_HEIGHT,8);


        Animation HurtUp = new Animation("./art/Soldier/Up/WarriorUpHurt.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation HurtDown = new Animation("./art/Soldier/Down/WarriorDownHurt.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation HurtLeft = new Animation("./art/Soldier/Left/WarriorLeftHurt.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation HurtRight = new Animation("./art/Soldier/Right/WarriorRightHurt.png",4,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);

        Animation DeadUp = new Animation("./art/Soldier/Up/WarriorUpDeath.png",5,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation DeadDown = new Animation("./art/Soldier/Down/WarriorDownDeath.png",5,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation DeadLeft = new Animation("./art/Soldier/Left/WarriorLeftDeath.png",5,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);
        Animation DeadRight = new Animation("./art/Soldier/Right/WarriorRightDeath.png",5,SPRITE_WIDTH,SPRITE_HEIGHT,8,false,null);


        Animation[] run = new Animation[] {RunUp, RunDown, RunLeft, RunRight};
        Animation[] attack = new Animation[] {AttackUp,AttackDown,AttackLeft,AttackRight};
        Animation[] idle = new Animation[] {IdleUP,IdleDown,IdleLeft,IdleRight};
        Animation[] hurt = new Animation[]{HurtUp,HurtDown,HurtLeft,HurtRight};
        Animation[] death = new Animation[]{DeadUp,DeadDown,DeadLeft,DeadRight};
        this.animations = new Animation[][] {idle, run,attack,hurt,death};
        this.action = IDLE;
    }
}
