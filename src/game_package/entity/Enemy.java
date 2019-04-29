package game_package.entity;

import game_package.graphics.Sprite;
import game_package.states.PlayState;
import game_package.util.KeyHandler;
import game_package.util.MouseHandler;
import game_package.util.Vector2f;

import java.awt.*;

public class Enemy extends Entity{

    public Enemy(Sprite sprt, Vector2f vector, int size, int hp) {
        super(sprt, vector, size, hp);

    }

    public void goToPlayerAndAttackHim(Vector2f posPlayer){
        System.out.println("I find him");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(animation.getImage(), (int)(pos.getWorldVar().x), (int)(pos.getWorldVar().y), size,
                size, null);
    }
}
