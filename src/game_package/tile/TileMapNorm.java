package game_package.tile;

import game_package.graphics.Sprite;
import game_package.tile.blocks.Block;
import game_package.tile.blocks.NormBlock;
import game_package.util.Vector2f;
import javafx.util.Pair;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TileMapNorm extends TileMap {

    public static HashMap<String, Block> blocks;

    public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight,
                       int tileColumns){
        blocks = new HashMap<String, Block>();

        String[] block_id = data.split(",");
        for(int i=0; i < (width*height); i++){
            int temp = Integer.parseInt(block_id[i].replaceAll("\\s+", ""));
            if (temp != 0){
                NormBlock block2 = new NormBlock(sprite.getSprite((int) ((temp - 1) % tileColumns),
                        (int) ((temp - 1) / tileColumns) ), new Vector2f((int) (i % width) * tileWidth,
                        (int) (i / height) * tileHeight), tileWidth, tileHeight);
                blocks.put(String.valueOf((int) (i % width) + "," + String.valueOf((int) (i / height))), block2);
            }
        }
    }

    public HashMap<String, String> build(int x, int y, Sprite sprite, int[][] sprit_id, int coulums){
//        Проверяет есть ли возможность построить что либо. Либо строит и возвращает true, сли что-то мешает, то не
//        то не строит и возвращает false.
        HashMap<String, String> result_changes = new HashMap<String, String>();
        x = x + 2;
        for (int i = 0; i<sprit_id.length; i++) {
            for (int j = 0; j < sprit_id[i].length; j++) {
                if (blocks.containsKey(String.format("%s,%s", x+i, y+j))){
                    return null;
                }
            }
        }
        for (int i = 0; i<sprit_id.length; i++) {
            for (int j = 0; j < sprit_id[i].length; j++) {
                String position = String.format("%s,%s", x+i, y+j);
                result_changes.put(position, Integer.toString(sprit_id[i][j]));
                blocks.put(position,
                        new NormBlock(sprite.getSprite((int)((sprit_id[i][j] - 1) % coulums),
                                                       (int)((sprit_id[i][j] - 1) / coulums)),
                                new Vector2f((x+i) * 64, (y + j) * 64), 64, 64));
            }
        }
        return result_changes;
    }

    public HashMap<String, String> destroy(int x, int y, int coulums) {
        HashMap<String, String> result_changes = new HashMap<String, String>();
        for (int i = -2; i < 2; i++) {
            for (int j = -2; j < 2; j++) {
                String position = String.format("%s,%s", x + i, y + j);
                if (blocks.get(position) != null) {
                    result_changes.put(position, "0");
                    blocks.remove(position);
                }
            }
        }
        if (result_changes.keySet().toArray().length != 0){
            return result_changes;
        }
        return null;
    }

    public void render(Graphics2D grphs, int x, int y){
        for (Block block: blocks.values()){
            block.render(grphs);
        }
    }
}
