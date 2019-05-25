package game_package.entity;

import game_package.graphics.Sprite;
import game_package.patterns.Observable;
import game_package.util.AABB;
import game_package.util.Vector2f;
import game_package.patterns.Observer;

import java.awt.*;


public class Enemy extends Entity {

    private boolean isFriendly;

    private AABB colls;

    private int maxHp = 250;
    private int hp = maxHp;
    private int power = 5;
    private double attackRecovery = 3.5;
    private Long lastAttack = System.currentTimeMillis();

    public Enemy(Sprite sprt, Vector2f vector, int size, boolean isFriendly) {
        super(sprt, vector, size);

        this.isFriendly = isFriendly;

        maxSpeed = 2f;
        acc = 1f;

        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);

        int test_r = 150;

        colls = new AABB(new Vector2f(vector.x - size / 2, vector.y - size / 2), test_r, this);
    }

    public boolean playerInCircle(Player posPlayer){
        return colls.colCircleBox(posPlayer.getBounds());
    }

    public boolean isAlive() { return this.hp > 0; }

    public Vector2f getPos() { return pos; }

    @Override
    public void gameCharacters(int hp, int power) {
        this.power = power;
        this.hp = hp;
    }

    public void update(Player player) {
        if ((hp > 0) && (! this.isFriendly)){
            super.update();
            move(player);
            if (!colls.collisionTile(dx, 0)) {
                colls.getPos().x += dx;
                pos.x += dx;
            }
            if (!colls.collisionTile(0, dy)) {
                colls.getPos().y += dy;
                pos.y += dy;
            }

            if (hitBounds.collides(player.getBounds())) {
                if (System.currentTimeMillis() > lastAttack + attackRecovery * 60) {
                    lastAttack = System.currentTimeMillis();
                    player.getHitted(this);
                }
            }
        }
    }

    public int getPower(){ return power; }

    public void getHitted(Player player){
        if (this.isFriendly){
            System.out.println("was here");
            this.isFriendly = false;
            this.hp -= player.getPower();

        }
        if (this.hp > 0){
            this.hp -= player.getPower();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (hp > 0) {
            g.setColor(Color.black);
            g.fillRect((int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, 5);
            g.setColor(Color.red);
            g.fillRect((int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y),
                    (int)(size * ((double)this.hp / (double)this.maxHp)), 5);
            g.drawImage(animation.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size,
                    size, null);

        }
    }


    @Override
    public void setDirectionsOnSprite(int up, int down, int left, int right) {
        UP = up;
        DOWN = down;
        RIGHT = right;
        LEFT = left;
    }

    private void setDirections(boolean _left, boolean _right, boolean _up, boolean _down){
        left = _left;
        right = _right;
        up = _up;
        down = _down;
    }

    public void move(Player player) {
        if (colls.colCircleBox(player.getBounds())) {
            if (pos.y > player.pos.y + 20) {
                down = false;
                up = true;
                dy -= acc;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            } else if (pos.y < player.pos.y - 20) {
                dy += acc;
                up = false;
                down = true;
                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
            } else {
                dy = 0;
                up = false;
                down = false;
            }

            if (pos.x > player.pos.x + 20) {
                dx -= acc;
                right = false;
                left = true;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            } else if (pos.x < player.pos.x - 20) {
                dx += acc;
                left = false;
                right = true;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
                }
            } else {
                dx = 0;
                right = false;
                left = false;
            }

        } else {
            setDirections(false, false, false, false);
            dx = 0;
            dy = 0;
        }
    }
}
