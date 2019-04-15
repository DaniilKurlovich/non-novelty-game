package game_package.tile;

import game_package.graphics.Sprite;
import game_package.tile.blocks.Block;
import game_package.tile.blocks.NormBlock;
import game_package.util.Vector2f;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TileMapNorm extends TileMap {

    private HashMap<String, Block> blocks;

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

    public void build(int x, int y, Sprite sprite, int[][] sprit_id, int coulums){
        for (int i = 0; i<sprit_id.length; i++) {
            for (int j = 0; j < sprit_id[i].length; j++) {
                if (blocks.containsKey(String.format("%s,%s", x+i, y+j))){
                    return;
                }
            }
        }
        for (int i = 0; i<sprit_id.length; i++) {
            for (int j = 0; j < sprit_id[i].length; j++) {
                blocks.put(String.format("%s,%s", x+i, y+j),
                        new NormBlock(sprite.getSprite((int)((sprit_id[i][j] - 1) % coulums),
                                                       (int)((sprit_id[i][j] - 1) / coulums)),
                                new Vector2f((x+i) * 64, (y + j) * 64), 64, 64));
            }
        }
    }

    public void render(Graphics2D grphs){
        for (Block block: blocks.values()){
            block.render(grphs);
        }
    }
}
