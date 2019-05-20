package game_package.entity;

import game_package.GamePanel;
import game_package.graphics.Sprite;
import game_package.states.PlayState;
import game_package.util.AABB;
import game_package.util.KeyHandler;
import game_package.util.MouseHandler;
import game_package.util.Vector2f;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Entity {

    private int power = 10;
    private int maxHp = 200;
    private int hp = maxHp;
    private ArrayList<Pair<String,int[][]>> buildings = new ArrayList<Pair<String,int[][]>>(
            Arrays.asList(new Pair<>("Лампа", new int[][]{{729, 761, 793}, {730, 762, 794}}),
                    new Pair<>("Лавка с клубникой", new int[][]{{601, 633, 665}, {602, 634, 666}}),
                    new Pair<>("Лавка с кукурузой", new int[][]{{607, 639, 671}, {608, 640, 672}}),
                    new Pair<>("Мешок", new int[][]{{535, 567}, {536, 568}}))
    );
    private int current_chouse;
    private boolean was_switched;
    private int attackRecovery = 1;
    private Long lastAttack = System.currentTimeMillis();

    public Player(Sprite sprite, Vector2f orgin, int size){
        super(sprite, orgin, size);
        acc = (float)2;
        maxSpeed = (float)3;
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);
        current_chouse = 0;
    }

    public Vector2f getPos(){ return this.pos; }

    @Override
    public void gameCharacters(int hp, int power) {
        this.hp = hp;
        this.power = power;
    }

    private void respawn(){
        this.hp = maxHp;
        pos.x = GamePanel.width / 2 - 32;
        PlayState.map.x = 0;

        pos.y = GamePanel.height / 2 - 32;
        PlayState.map.y = 0;
    }

    public void getHitted(Enemy enemy){
        if (this.hp > 0){
            this.hp -= enemy.getPower();
        } else {
            respawn();
        }
    }

    public int getPower() { return this.power; }

    public void update(Enemy enemy) {
        super.update();

        if (System.currentTimeMillis() > lastAttack + attackRecovery * 60) {
            if (hitBounds.collides(enemy.getBounds()) && attack) {
                enemy.getHitted(this);
                lastAttack = System.currentTimeMillis();
            }
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
        if (attack){
            g.setColor(Color.red);
            g.drawRect((int) (hitBounds.getPos().getWorldVar().x + hitBounds.getxOffset()),
                    (int) (hitBounds.getPos().getWorldVar().y + hitBounds.getyOffset()),
                    (int)hitBounds.getWidth(), (int)hitBounds.getHeight());
        }

        if (hp > 0) {
            g.setColor(Color.black);
            g.fillRect((int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, 5);
            g.setColor(Color.red);
            g.fillRect((int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y),
                    (int)(size * ((double)this.hp / (double)this.maxHp)), 5);
            g.drawImage(animation.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size,
                    size, null);

        }

        g.drawImage(animation.getImage(), (int)(pos.getWorldVar().x), (int)(pos.getWorldVar().y), size,
                size, null);
        g.drawString(buildings.get(current_chouse).getKey(), 0, 50);
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
        if (key.build.isDown){
            build = true;
        }
        else {
            build = false;
        }

        //System.out.println(mouse.getButton());

        if (key.switch_building.isDown){
            if (!was_switched)
                current_chouse = (current_chouse + 1) % buildings.size();
            was_switched = true;
        }
        else{
            was_switched = false;
        }
    }

    public void action(){
        if (action){
            PlayState.tm.build(pos.x, pos.y, buildings.get(current_chouse).getValue());
        }
        action = false;
        if (attack){
            PlayState.tm.destroy(pos.x, pos.y);
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