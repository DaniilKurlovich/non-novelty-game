package game_package.entity;

import game_package.graphics.Sprite;
import game_package.util.AABB;
import game_package.util.Vector2f;

import java.awt.*;

public class Enemy extends Entity {

    private AABB colls;

    public Enemy(Sprite sprt, Vector2f vector, int size, int hp) {
        super(sprt, vector, size, hp);

        maxSpeed = 2f;
        acc = 1f;

        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);

        int test_r = 150;

        colls = new AABB(new Vector2f(vector.x - size / 2, vector.y - size / 2), test_r, this);
    }

    public void update(Player player) {
        move(player);
        if (!colls.collisionTile(dx, 0)) {
            colls.getPos().x += dx;
            pos.x += dx;
        }
        if (!colls.collisionTile(0, dy)) {
            colls.getPos().y += dy;
            pos.y += dy;
        }

    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(animation.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size,
                size, null);
    }

    public void move(Player player) {
        if (colls.colCircleBox(player.getBounds())) {
            if (pos.y > player.pos.y) {
                dy -= acc;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            } else {
                if (dy < 0) {
                    dy += deacc;
                    if (dy > 0) {
                        dy = 0;
                    }
                }
            }

            if (pos.y < player.pos.y) {
                dy += acc;
                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
            } else {
                if (dy > 0) {
                    dy -= deacc;
                    if (dy < 0) {
                        dy = 0;
                    }
                }
            }

            if (pos.x > player.pos.x) {
                dx -= acc;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            } else {
                if (dx < 0) {
                    dx += deacc;
                    if (dx > 0) {
                        dx = 0;
                    }
                }
            }

            if (pos.x < player.pos.x) {
                dx += acc;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
                }
            } else {
                if (dx > 0) {
                    dx -= deacc;
                    if (dx < 0) {
                        dx = 0;
                    }
                }
            }
        } else {
            dx = 0;
            dy = 0;
        }
    }
}
