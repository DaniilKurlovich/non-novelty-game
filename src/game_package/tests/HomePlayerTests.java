package game_package.tests;

import game_package.entity.HomePlayer;
import game_package.util.Vector2f;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HomePlayerTests {
    private HomePlayer homePlayer;

    @Before
    public void initTest(){
        homePlayer = new HomePlayer();
        homePlayer.setFirstCoordinate(new Vector2f(0, 0));
        homePlayer.setSecondCoordinate(new Vector2f(20, 20), 100);
    }


}
