package game_package.GameObserver;

import game_package.entity.Enemy;
import game_package.entity.Player;
import game_package.graphics.Sprite;
import game_package.patterns.Observer;
import game_package.states.PlayState;
import game_package.util.Vector2f;

import java.util.*;

public class PlayerAndEnemiesInteractions implements Observer {
    private List<Enemy> observers = new ArrayList<>();   // enemies
    private Player player;
    private int countsEnemy = 0;
    private List monsterCountProgression = new LinkedList(){{
        add(5); add(10); add(15); add(20);
    } };
    private int curProgressionStep = 0;
    private int[] coordinateOfSpawn;

    private long lastUpdate = System.currentTimeMillis();
    private int checkingDelay = 1;

    public PlayerAndEnemiesInteractions(Player player, int[] coordinateSpawn){
        this.player = player;
        this.coordinateOfSpawn = coordinateSpawn;
    }

    public void addEnemy(Enemy enemy){
        countsEnemy += 1;
        observers.add(enemy);
    }

    public void removeEnemy(Enemy enemy, boolean isGenerateNewEnemy){
        countsEnemy -= 1;
        if ((countsEnemy == 0) && (isGenerateNewEnemy)){
            curProgressionStep += 1;
            if (curProgressionStep >= monsterCountProgression.size()){
                curProgressionStep = (int)monsterCountProgression.get(monsterCountProgression.size() - 1);
                spawn_monsters();
            }
        }
        observers.remove(enemy);
    }

    public Enemy getRandomEnemy(){
        String[] spriteArray = {
                "resource/entity/1.png",
                "resource/entity/2.png",
                "resource/entity/3.png",
                "resource/entity/4.png"};
        Random rnd = new Random();
        int curSpriteIndex = rnd.nextInt(spriteArray.length);
        Enemy enemy = new Enemy(new Sprite(spriteArray[curSpriteIndex], 64, 64),
                      new Vector2f(coordinateOfSpawn[0], coordinateOfSpawn[1]), 64, true);
        enemy.setDirectionsOnSprite(3, 0, 1, 2);
        return enemy;
    }

    public void spawn_monsters(){
        for(int i=0; i < monsterCountProgression.size(); i++){
            Enemy enemy = getRandomEnemy();
            addEnemy(enemy);
        }
    }

    public void handleEnemies(List<Enemy> deadEnemies){
        for (Enemy enemy: deadEnemies){
            removeEnemy(enemy, player.playerHaveHome());
        }
    }

    // к этому методу будет обращаться Enemy со своими координатами
    @Override
    public void update() {
        if (System.currentTimeMillis() > lastUpdate + checkingDelay){
            List<Enemy> enemies = new LinkedList<>();
            for (Enemy enemy: observers){
                player.updateWithEnemy(enemy);
                enemy.update(player);
                if (!enemy.isAlive()){
                    enemies.add(enemy);
                }
            }
            handleEnemies(enemies);
        }
        lastUpdate = System.currentTimeMillis();
    }

}

