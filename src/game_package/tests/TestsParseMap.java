//package game_package.tests;
//
//
//import game_package.tile.TileManager;
//import static org.junit.Assert.*; // кавооо, что за static импорты?
//
//import game_package.tile.TileMap;
//import game_package.tile.TileMapNorm;
//import game_package.tile.TileMapObj;
//import org.junit.Test;
//
//public class TestsParseMap {
//    @Test
//    public void parseMapTestCoordinates() {
//        String[] obj_coor = new String[]{"0,0", "1,0", "0,1", "1,1"};
//        String[] norm_coor = new String[]{"0,0", "1,0", "0,1", "1,1"};
//        TileManager tileTest = new TileManager("src/resource/test_rsc/test_map.xml",
//                "resource/tile/map_tile.png");
//        boolean isAllCoordinates = true;
//        for (int i = 0; i < tileTest.tm.size(); i++) {
//            if (tileTest.tm.get(i) instanceof TileMapObj) {
//                for (String j : obj_coor)
//                    isAllCoordinates = ((TileMapObj) tileTest.tm.get(i)).blocks.containsKey(j);
//                if (!isAllCoordinates)
//                    break;
//
//                if (tileTest.tm.get(i) instanceof TileMapNorm) {
//                    for (String j : norm_coor)
//                        isAllCoordinates = ((TileMapObj) tileTest.tm.get(i)).blocks.containsKey(j);
//                    if (!isAllCoordinates)
//                        break;
//                }
//            }
//            assertTrue(isAllCoordinates);
//        }
//    }
//}
