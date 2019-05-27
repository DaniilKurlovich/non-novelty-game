package game_package.tests;

import game_package.GamePanel;
import game_package.entity.Enemy;
import game_package.entity.HomePlayer;
import game_package.entity.Player;
import game_package.graphics.Sprite;
import game_package.util.Vector2f;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestEnemyAndHomePlayer {

    private Enemy enemy;
    private HomePlayer homePlayer;

    @Before
    public void initTest(){
        homePlayer = new HomePlayer();
        homePlayer.setFirstCoordinate(new Vector2f(381, 579));
        homePlayer.setSecondCoordinate(new Vector2f(632, 836), 100);
        enemy = new Enemy(new Sprite("resource/entity/1.png", 32, 32),
                new Vector2f(554, 561), 64,
                false);
    }

    @Test
    public void enemyAttackHome(){
        enemy.gameCharacters(100, 100);
        homePlayer.getHitted(enemy);
        System.out.println(homePlayer.getCurHp());
        assertTrue(homePlayer.homeIsBroke());
    }

    @Test
    public void testBounds(){
        assertTrue(enemy.collisionWith(homePlayer.getBounds()));
    }
}
