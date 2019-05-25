package game_package.entity;

import game_package.graphics.Sprite;
import game_package.util.AABB;
import game_package.util.Vector2f;

import java.awt.*;

public class Tower extends Entity{
    private AABB colls;
    private int maxHp;
    private int hp;
    private int power;

    private double attackRecovery;
    private Long lastAttack = System.currentTimeMillis();

    private int radiusAttack;

    public Tower(Sprite sprt, Vector2f vector, int size) {
        super(sprt, vector, size);
    }

    @Override
    public void gameCharacters(int hp, int power) {
        this.hp = hp;
        this.power = power;
    }

    public boolean enemyInCircle(Enemy enemy){
        return false;
    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void setDirectionsOnSprite(int up, int down, int left, int right) {

    }
}
