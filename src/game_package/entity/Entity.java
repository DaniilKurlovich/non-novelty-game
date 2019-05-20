package game_package.entity;

import game_package.graphics.Animation;
import game_package.graphics.Sprite;
import game_package.util.AABB;
import game_package.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected int UP = 3;
    protected int DOWN = 2;
    protected int RIGHT = 0;
    protected int LEFT = 1;
    protected int currentAnimation;

    protected Animation animation;
    private Sprite sprite;
    protected int size;
    protected Vector2f pos;

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;
    protected boolean action;
    protected boolean attack;
    protected boolean build;
    protected boolean switch_build;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed = 4f;
    protected float acc = 3f;
    protected float deacc = 0.3f;

    protected AABB hitBounds;
    protected AABB bounds;

    private int hp;
    private int maxHp;

    public Entity(Sprite sprt, Vector2f vector, int size) {
        // graphics
        this.sprite = sprt;
        this.pos = vector;
        this.size = size;

        bounds = new AABB(vector, size, size);
        hitBounds = new AABB(vector, size, size);
        hitBounds.setXOffset(size / 2);

        animation = new Animation();
        setAnimation(DOWN, sprt.getSpriteArrayWithIndex(RIGHT), 10);
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public abstract void gameCharacters(int hp, int power);

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public void setMaxSpeed(int speed) { this.maxSpeed = speed; }
    public void setAcc(float f) { acc = f; }
    public void setDeacc(float f) { deacc = f; }

    public AABB getBounds() { return bounds; }


    public Animation getAnimation() { return animation; }

    public void setAnimation(int side, BufferedImage[] animation, int delay_animation){
        currentAnimation = side;
        this.animation.setAnimation(animation);
        this.animation.setDelay(delay_animation);
    }

    public void animate() {
        if (up) {
            if (currentAnimation != UP || animation.getDelay() == -1){
                setAnimation(UP, sprite.getSpriteArrayWithIndex(UP), 5);
            }
        }
        else if (down) {
            if (currentAnimation != DOWN || animation.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArrayWithIndex(DOWN), 5);
            }
        }
        else if (left) {
            if (currentAnimation != LEFT || animation.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArrayWithIndex(LEFT), 5);

            }
        }
        else if (right) {
            if (currentAnimation != RIGHT || animation.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArrayWithIndex(RIGHT), 5);
            }
        }
        else {
            setAnimation(currentAnimation, sprite.getSpriteArrayWithIndex(currentAnimation), -1);
        }
    }

    private void setHitBoxDirection() {
        if (up){
            hitBounds.setYOffset(-size / 2);
            hitBounds.setXOffset(0);
        }
        else if (down){
            hitBounds.setYOffset(size / 2);
            hitBounds.setXOffset(0);
        }
        else if (left){
            hitBounds.setXOffset(-size / 2);
            hitBounds.setYOffset(0);
        }
        else if (right){
            hitBounds.setYOffset(0);
            hitBounds.setXOffset(size / 2);
        }
    }

    public void update() {
        animate();
        setHitBoxDirection();
        animation.update();
    }

    public abstract void render(Graphics2D g);

    public abstract void setDirectionsOnSprite(int up, int down, int left, int right);
}
