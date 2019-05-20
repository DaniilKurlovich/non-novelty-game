package game_package.patterns;

import game_package.entity.Enemy;
import game_package.entity.Player;
import game_package.util.Vector2f;

public interface Observer {
    void update (Player player);
}