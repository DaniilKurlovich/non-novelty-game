package game_package.GameObserver;

import game_package.entity.Enemy;
import game_package.entity.Player;
import game_package.patterns.Observer;

import java.util.HashMap;

public class PlayerAndEnemiesInteractions implements Observer {
    private HashMap<String,Enemy> observers = new HashMap<>();   // enemies
    private Player player;

    private long lastUpdate = System.currentTimeMillis();
    private int checkingDelay = 1;

    public PlayerAndEnemiesInteractions(Player player){
        this.player = player;
    }

    public void addEnemy(Enemy enemy){
        observers.put(String.format("%s, %s", enemy.getPos().x, enemy.getPos().y), enemy);
    }

    public void removeEnemy(){}

    // к этому методу будет обращаться Enemy со своими координатами
    @Override
    public void update() {
        if (System.currentTimeMillis() > lastUpdate + checkingDelay){
            observers.forEach((key, value) -> {
                if (value.playerInCircle(player)){
                    value.update(player);
                    player.updateWithEnemy(value);
                }
            });
            lastUpdate = System.currentTimeMillis();
        }
    }

}
