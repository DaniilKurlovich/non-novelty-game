package game_package.entity;

import game_package.graphics.Sprite;
import game_package.util.KeyHandler;
import game_package.util.MouseHandler;
import game_package.util.Vector2f;

import java.awt.*;

public class Player extends Entity {

    public Player(Sprite sprite, Vector2f orgin, int size){
        super(sprite, orgin, size);
    }

    public void update() {
        super.update();
        move();
        pos.x += dx;
        pos.y += dy;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(animation.getImage(), (int)(pos.x), (int)(pos.y), size, size, null);
    }

    public void input(MouseHandler mouse, KeyHandler key){

        if (mouse.getButton() == -1) {
            System.out.println(String.format("Player %s, %s", pos.x, pos.y));
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
