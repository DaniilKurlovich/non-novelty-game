package game_package.tile.blocks;

import game_package.util.AABB;
import game_package.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HoleBlock extends Block {

    public HoleBlock(BufferedImage image, Vector2f pos, int w, int h){
        super(image, pos, w, h);
    }

    @Override
    public boolean update(AABB col) {

        if (isInside(col)){
            System.out.println("TEST");
        }

        return false;
    }

    public boolean isInside(AABB coll){
        if (coll.getPos().x + coll.getxOffset() < pos.x) return true;
        if (coll.getPos().y + coll.getyOffset() < pos.y) return true;
        if (w + pos.x < coll.getWidth() + (coll.getPos().x + coll.getxOffset())) return false;
        if (h + pos.y < coll.getHeight() + (coll.getPos().y + coll.getyOffset())) return false;
        return true;
    }

    public void render(Graphics2D grphs){
        grphs.setColor(Color.darkGray);
        grphs.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h);
        super.render(grphs);
    }
}
