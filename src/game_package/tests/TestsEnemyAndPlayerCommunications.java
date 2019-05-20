package game_package.tests;

import game_package.GamePanel;
import game_package.entity.Enemy;
import game_package.entity.Player;
import game_package.graphics.Sprite;
import game_package.tile.TileManager;
import game_package.util.Vector2f;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestsEnemyAndPlayerCommunications {

    private Player player;
    private Enemy enemy;

    @Before
    public void initTest(){
        player = new Player(new Sprite("resource/entity/linkformatted.png", 32, 32),
                new Vector2f((int) GamePanel.width / 2,(int)GamePanel.height / 2), 64);
        enemy = new Enemy(new Sprite("resource/entity/test.png", 32, 32),
                new Vector2f((int) GamePanel.width / 2 + 45,(int)GamePanel.height / 2 + 70), 64);
    }


    @Test
    public void checkWinPlayer(){
        player.gameCharacters(10000, 200);
        enemy.gameCharacters(200, 100);
        enemy.getHitted(player);
        assertFalse(enemy.isAlive());
        initTest();
    }

    @Test
    public void checkWinEnemy(){
        player.gameCharacters(200, 100);
        enemy.gameCharacters(10000, 200);
        player.getHitted(enemy);
        assertFalse(player.isAlive());
        initTest();
    }

}
