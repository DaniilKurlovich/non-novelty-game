package game_package.entity;

import game_package.graphics.Sprite;
import game_package.patterns.PositionData;
import game_package.states.PlayState;
import game_package.util.KeyHandler;
import game_package.util.MouseHandler;
import game_package.util.Vector2f;

import java.awt.*;

public class Player extends Entity {

    private int power = 5;
    private int hp = 100;

    public Player(Sprite sprite, Vector2f orgin, int size){
        super(sprite, orgin, size);
        acc = (float)2;
        maxSpeed = (float)3;
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);
    }

    @Override
    public void GameCharacters(int hp, int power) {
        this.hp = hp;
        this.power = power;
    }

    public void update(Enemy enemy) {
        super.update();

        if (hitBounds.collides(enemy.getBounds()) && attack){
            enemy.getHitted(this.power);
            System.out.println("Enemy hitted");
        }

        action();
        move();
        if (!bounds.collisionTile(dx, 0)) {
            PlayState.map.x += dx;
            pos.x += dx;
        }
        if (!bounds.collisionTile(0, dy)) {
            PlayState.map.y += dy;
            pos.y += dy;
        }
        PlayState.observer.setPosition(pos);
    }

    @Override
    public void render(Graphics2D g) {
        //g.setColor(Color.blue);
        //g.drawRect((int)(pos.getWorldVar().x + bounds.getxOffset()), (int)(pos.getWorldVar().y + bounds.getyOffset()),
        //           (int)bounds.getWidth(), (int)bounds.getHeight());

        if (attack){
            g.setColor(Color.red);
            g.drawRect((int) (hitBounds.getPos().getWorldVar().x + hitBounds.getxOffset()),
                       (int) (hitBounds.getPos().getWorldVar().y + hitBounds.getyOffset()),
                       (int)hitBounds.getWidth(), (int)hitBounds.getHeight());
        }

        g.drawImage(animation.getImage(), (int)(pos.getWorldVar().x), (int)(pos.getWorldVar().y), size,
                    size, null);
    }

    @Override
    public void setDirectionsOnSprite(int up, int down, int left, int right) {
        UP = up;
        DOWN = down;
        LEFT = left;
        RIGHT = right;
    }

    public void input(MouseHandler mouse, KeyHandler key){
        if (mouse.getButton() != -1) {
            attack = true;
        } else {
            attack = false;
        }
        if (key.up.isDown){
            up = true;
        } else {
            up = false;
        }
        if (key.left.isDown){
            left = true;
        } else {
            left = false;
        }
        if (key.right.isDown){
            right = true;
        } else {
            right = false;
        }
        if (key.down.isDown){
            down = true;
        } else {
            down = false;
        }
        if (key.action.isDown){
            action = true;
        } else {
            action = false;
        }
    }

    public void action(){
        if (action){
            PlayState.tm.build(pos.x, pos.y, new int[][]{{729, 761, 793}, {730, 762, 794}});
        }
        action = false;
    }

    public void move(){
        if(up) {
            dy -= acc;
            if(dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if(dy < 0) {
                dy += deacc;
                if(dy > 0) {
                    dy = 0;
                }
            }
        }

        if(down) {
            dy += acc;
            if(dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if(dy > 0) {
                dy -= deacc;
                if(dy < 0) {
                    dy = 0;
                }
            }
        }

        if(left) {
            dx -= acc;
            if(dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if(dx < 0) {
                dx += deacc;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }

        if(right) {
            dx += acc;
            if(dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if(dx > 0) {
                dx -= deacc;
                if(dx < 0) {
                    dx = 0;
                }
            }
        }

    }
}
