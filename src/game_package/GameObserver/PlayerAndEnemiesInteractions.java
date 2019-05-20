package game_package.GameObserver;

import game_package.entity.Enemy;
import game_package.entity.Player;
import game_package.patterns.Observer;
import game_package.util.Vector2f;

import java.util.List;

public class PlayerAndEnemiesInteractions implements Observer {
    private List<Enemy> observers;   // enemies
    private Player player;

    public PlayerAndEnemiesInteractions(Player player){
        this.player = player;
    }


    // к этому методу будет обращаться Enemy со своими координатами
    @Override
    public void update(Player player) {

    }

}
