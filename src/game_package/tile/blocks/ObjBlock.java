package game_package.tile.blocks;

import game_package.util.AABB;
import game_package.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjBlock extends Block {

    public ObjBlock(BufferedImage image, Vector2f pos, int w, int h){
        super(image, pos, w, h);
    }

    @Override
    public boolean update(AABB col) {
        return true;
    }

    @Override
    public boolean isInside(AABB col) {
        return false;
    }

    public void render(Graphics2D grphs){
        grphs.setColor(Color.white);
        grphs.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h);
        super.render(grphs);
    }
}
