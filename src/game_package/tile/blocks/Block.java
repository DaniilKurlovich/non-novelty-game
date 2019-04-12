package game_package.tile.blocks;

import game_package.util.AABB;
import game_package.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    protected int w;
    protected int h;

    protected BufferedImage img;
    protected Vector2f pos;

    public Block(BufferedImage img, Vector2f pos, int w, int h){
        this.pos = pos;
        this.img = img;
        this.w = w;
        this.h = h;
    }

    public abstract boolean update(AABB col);

    public void render(Graphics2D grps){
        grps.drawImage(img, (int)pos.getWorldVar().x, (int)pos.getWorldVar().y, w, h, null);
    }
}
