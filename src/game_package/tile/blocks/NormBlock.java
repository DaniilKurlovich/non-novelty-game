package game_package.tile.blocks;

import game_package.util.AABB;
import game_package.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormBlock extends Block {

    public NormBlock(BufferedImage img, Vector2f pos, int w, int h){
        super(img, pos, w, h);
    }

    @Override
    public boolean update(AABB col) {
        return true;
    }

    public void render(Graphics2D grphs){
        grphs.setColor(Color.white);
        grphs.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h);
        super.render(grphs);
    }
}
