package game_package.states;

import game_package.entity.Player;
import game_package.graphics.Sprite;
import game_package.tile.TileManager;
import game_package.util.KeyHandler;
import game_package.util.MouseHandler;
import game_package.graphics.Font;
import game_package.util.Vector2f;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private TileManager tm;

    public PlayState(GameStateManager gsm){
        super(gsm);
        tm = new TileManager("resource/tile/map.xml");
        //font = new Font("resource/font/first_font.png", 16, 16);
        player = new Player(new Sprite("resource/entity/linkformatted.png", 32, 32), new Vector2f(100, 100), 64);
    }

    public void update(){
        player.update();
    }
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
        if (key.up.isDown){
            System.out.println("Down pressed");
        }
    }
    public void render(Graphics2D grphs) {
        tm.render(grphs);
        //Sprite.drawArray(grphs, font, "KEK", new Vector2f(100, 100), 32, 32,
        //        12,0);
        player.render(grphs);
    }

}
