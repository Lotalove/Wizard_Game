import java.awt.*;

public abstract class Character1 extends Sprite {

    // -- Shared animation state --
    protected int health;
    protected int maxHealth;
    protected int speed=5 ;
    protected boolean dealingDamage = false;
    protected boolean alive = true;
    protected boolean droppedLoot = false;

    static final int IDLE = 0;
    static final int WALKING = 1;
    static final int ATTACKING = 2;
    static final int HURT = 3;
    static final int DEAD = 4;

    protected final int attackRadius;
    protected final int xOffset;
    protected final int yOffset;
    protected final int widthOffset;
    protected final int heightOffset;

    protected boolean computer_controlled;
    int ai_sight_range;

    public Character1(int x, int y, int spriteWidth, int spriteHeight, double scale,
                      int health, int attackRadius,
                      int xOffset, int yOffset, int widthOffset, int heightOffset) {
        super(x, y, spriteWidth, spriteHeight, scale);
        this.health = health;
        this.maxHealth = health;
        this.attackRadius = attackRadius;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.widthOffset = widthOffset;
        this.heightOffset = heightOffset;
        this.hitbox = new Rect(x + xOffset, y + yOffset, spriteWidth - widthOffset, spriteHeight - heightOffset);
    }

    // -- Shared movement --
    public void moveUp() {
        this.y -= speed;
        this.hitbox.y -= speed;
        this.direction = UP;
        updateAction(WALKING);
    }

    public void moveDown() {
        this.y += speed;
        this.hitbox.y += speed;
        this.direction = DOWN;
        updateAction(WALKING);
    }

    public void moveLeft() {
        this.x -= speed;
        this.hitbox.x -= speed;
        this.direction = LEFT;
        updateAction(WALKING);
    }

    public void moveRight() {
        this.x += speed;
        this.hitbox.x += speed;
        this.direction = RIGHT;
        updateAction(WALKING);
    }

    public void knockBack(Character1 c){
        int amount = c.attackRadius;
        if (c.direction == UP){
            this.y -= amount;
            this.hitbox.y -= amount;

        }
        else if (c.direction == DOWN){
            this.y += amount;
            this.hitbox.y += amount;
        }
        else if (c.direction == LEFT){
            this.x -= amount;
            this.hitbox.x -= amount;
        }
        else if (c.direction == RIGHT){
            this.x += amount;
            this.hitbox.x += amount;
        }
    }
    // -- Shared action logic --
    public void updateAction(int action) {
        if (this.action != action) {
            animations[this.action][this.direction].reset();
            this.action = action;
        }
    }

    public void attack() {
        this.action = ATTACKING;
    }

    public boolean isAttacking() {
        return this.action == ATTACKING;
    }

    public void takeDamage(int damage) {
        if (!this.alive) return;
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            this.action = DEAD;
            droppedLoot = Math.random() < 1.0;
        } else {
            this.action = HURT;
            this.receivingDamage = true;
        }
    }

    public boolean collidesWith(Rect r) {
        return hitbox.overlaps(r);
    }

    // -- Shared damage box logic --
    public Rect getDamageBox() {
        if (this.direction == RIGHT)
            return new Rect(hitbox.x + attackRadius, hitbox.y, attackRadius, attackRadius);
        else if (this.direction == LEFT)
            return new Rect(hitbox.x - attackRadius, hitbox.y, attackRadius, attackRadius);
        else if (this.direction == UP)
            return new Rect(hitbox.x, hitbox.y - attackRadius, attackRadius, attackRadius);
        else
            return new Rect(hitbox.x, hitbox.y + attackRadius, attackRadius, attackRadius);
    }

    public void drawAttackBox(Graphics g) {
        getDamageBox().draw(g);
    }

    // -- Shared update loop --
    public void update() {
        this.x = this.hitbox.x - this.xOffset;
        this.y = this.hitbox.y - this.yOffset;

        this.dealingDamage = false;

        Animation currAnim = this.animations[this.action][this.direction];
        if (this.action == ATTACKING && currAnim.isKeyFrame()) {
            this.dealingDamage = true;
        }
        if (this.action == DEAD && currAnim.isFinished()) {
            this.alive = false;
            this.visible=false;

        }

        if (currAnim.isFinished()) {
            currAnim.reset();
            this.action = IDLE;
        }

        if(computer_controlled && this.action != DEAD) {
            ai();
        }
    }

    public void drawHealthBar(Graphics g) {
        int barWidth = 60;
        int barHeight = 8;
        int barX = this.x + (this.width/ 2) - (barWidth / 2); // centered above sprite
        int barY = this.y - 16;

        float healthPercent = (float) this.health / this.maxHealth;

        // 1. Gray background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(barX-Camera.x, barY-Camera.y, barWidth, barHeight);

        // 2. Green/yellow/red fill depending on health
        if (healthPercent > 0.5f)       g.setColor(Color.GREEN);
        else if (healthPercent > 0.25f) g.setColor(Color.ORANGE);
        else                            g.setColor(Color.RED);

        g.fillRect(barX-Camera.x, barY-Camera.y, (int) (barWidth * healthPercent), barHeight);

        // 3. Black outline
        g.setColor(Color.BLACK);
        g.drawRect(barX-Camera.x, barY-Camera.y, barWidth, barHeight);
    }
    public void ai(){
        double distanceFromPlayer = Math.sqrt(Math.pow(this.x-Game.player.x,2) + Math.pow(this.y-Game.player.y,2));
        if (distanceFromPlayer > ai_sight_range) {return;}
        if(distanceFromPlayer < attackRadius){
            if(this.action != ATTACKING && this.action != HURT ){
                attack();
            }
        }
        else {
            chase();
        }
    }

    public void chase() {
        int dx = x - Game.player.x;
        int dy = y - Game.player.y;


        if (Math.abs(dx) > speed) {
            if (dx > 0) moveLeft();
            else moveRight();
        }

        if (Math.abs(dy) > speed) {
            if (dy > 0) moveUp();
            else moveDown();
        }
    }
    // -- Subclasses must load their own animations --
    protected abstract void loadAnimations();
}