package game_package.states;

import game_package.util.KeyHandler;
import game_package.util.MouseHandler;

import java.awt.*;

public abstract class GameState {

    private GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void render(Graphics2D graphics);
    public abstract void input(MouseHandler mouse, KeyHandler key);
}
