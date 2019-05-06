package game_package.states;

import game_package.GamePanel;
import game_package.entity.Enemy;
import game_package.entity.Player;
import game_package.graphics.Sprite;
import game_package.patterns.PositionData;
import game_package.tile.TileManager;
import game_package.util.KeyHandler;
import game_package.util.MouseHandler;
import game_package.graphics.Font;
import game_package.util.Vector2f;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    public static TileManager tm;

    public static Vector2f map;
    public static PositionData observer;

    public Enemy testEnemy;

    public PlayState(GameStateManager gsm){
        super(gsm);
        tm = new TileManager("src/resource/tile/map.xml", "resource/tile/map_tile.png");
        map = new Vector2f();
        observer = new PositionData();

        System.out.println(map.toString());

        Vector2f.setWorldVar(map.x, map.y);

        player = new Player(new Sprite("resource/entity/linkformatted.png", 32, 32),
                            new Vector2f((int) GamePanel.width / 2,(int)GamePanel.height / 2), 64, 100);

        testEnemy = new Enemy(new Sprite("resource/entity/linkformatted.png", 32, 32),
                              new Vector2f((int) GamePanel.width / 2 + 45,(int)GamePanel.height / 2 + 70), 64, 12);

    }

    public void update(){
        Vector2f.setWorldVar(map.x, map.y);
        testEnemy.update(player);
        player.update();
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }
    public void render(Graphics2D grphs) {
        tm.render(grphs,(int)Vector2f.worldX, (int)Vector2f.worldY );
        player.render(grphs);
        testEnemy.render(grphs);
    }

}
