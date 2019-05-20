package game_package.tests;


import game_package.tile.TileManager;
import static org.junit.Assert.*;

import game_package.tile.TileMap;
import game_package.tile.TileMapNorm;
import game_package.tile.TileMapObj;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TestsParseMap {

    private TileManager tmBackupBuild;

    @Before
    public void initTest(){
       tmBackupBuild = new TileManager("src/resource/test_rsc/test_map.xml",
               "resource/tile/map_tile.png");
    }

    @Test
    public void parseMapTestCoordinates() {
        String[] obj_coor = new String[]{"0,0", "1,0", "0,1", "1,1"};
        String[] norm_coor = new String[]{"0,0", "1,0", "0,1", "1,1"};
        TileManager tileTest = new TileManager("src/resource/test_rsc/test_map.xml",
                "resource/tile/map_tile.png");
        boolean isAllCoordinates = true;
        for (int i = 0; i < tileTest.tm.size(); i++) {
            if (tileTest.tm.get(i) instanceof TileMapObj) {
                for (String j : obj_coor)
                    isAllCoordinates = ((TileMapObj) tileTest.tm.get(i)).blocks.containsKey(j);
                if (!isAllCoordinates)
                    break;

                if (tileTest.tm.get(i) instanceof TileMapNorm) {
                    for (String j : norm_coor)
                        isAllCoordinates = ((TileMapObj) tileTest.tm.get(i)).blocks.containsKey(j);
                    if (!isAllCoordinates)
                        break;
                }
            }
            assertTrue(isAllCoordinates);
        }
    }

    @Test
    public void testBuild(){
        TileManager tileBackup = new TileManager("src/resource/test_rsc/test_map.xml",
                "resource/tile/map_tile.png");
        TileManager tileTest = new TileManager("src/resource/test_rsc/test_map.xml",
                "resource/tile/map_tile.png");
        int[][] changes = new int[2][2];
        changes[0][0] = 1;
        changes[0][1] = 1;
        changes[1][0] = 1;
        changes[1][1] = 1;
        tileTest.build(0, 0, changes);

        TileManager testBuild = new TileManager("src/resource/test_rsc/test_map.xml",
                "resource/tile/map_tile.png");

        //System.out.println(((TileMapObj)testBuild.tm.get(0)).blocks.get("0,1"));

    }

    @Test
    public void testDestroy(){

    }


}
