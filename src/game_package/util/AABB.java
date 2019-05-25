package game_package.util;

import game_package.entity.Entity;
import game_package.tile.TileMapNorm;
import game_package.tile.TileMapObj;
import game_package.tile.blocks.Block;
import game_package.tile.blocks.HoleBlock;
import game_package.tile.blocks.NormBlock;
import game_package.tile.blocks.ObjBlock;

import java.util.List;

public class AABB {

    private Vector2f pos;
    private float xOffset = 0;
    private float yOffset = 0;
    private float w;
    private float h;
    private float r;
    private Entity entity;
    private int size;

    public AABB(Vector2f pos, int w, int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public AABB(Vector2f pos, int r, Entity ent){
        this.pos = pos;
        this.r = r;
        this.entity = ent;
        size = r;
    }

    public AABB(Vector2f firstPos, Vector2f secondPos){
        List compareVectors = firstPos.compareAndGetDelta(secondPos);
        this.pos = (Vector2f) compareVectors.get(0);
        this.w = (int) compareVectors.get(1);
        this.h = (int) compareVectors.get(2);
    }

    public Vector2f getPos() { return this.pos; }
    public float getRadius() { return r; }
    public float getWidth() { return w; }
    public float getHeight() { return h; }

    public void setBox(Vector2f pos, int w, int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public void setCircle(Vector2f pos, int r){
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public void setWidth(float w){ this.w = w; }
    public void setHeight(float h){ this.h = h; }

    public void setXOffset(float offset){ this.xOffset = offset; }
    public void setYOffset(float offset) { this.yOffset = offset; }

    public float getyOffset() { return this.yOffset; }
    public float getxOffset() { return this.xOffset; }


    public boolean collides(AABB bBox){
        float ax = pos.getWorldVar().x + xOffset + this.w / 2;
        float ay = pos.getWorldVar().y + yOffset + this.h / 2;
        float bx = bBox.pos.getWorldVar().x + bBox.xOffset / 2 + bBox.getWidth() / 2;
        float by = bBox.pos.getWorldVar().y + bBox.yOffset / 2 + bBox.getHeight() / 2;

        if (Math.abs(ax - bx) < (this.w / 2) + (bBox.getWidth() / 2)) {
            if (Math.abs(ay - by) < (this.h / 2) + (bBox.getHeight() / 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean colCircleBox(AABB aBox) {
        float cx = (float) (pos.getWorldVar().x + r / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));
        float cy = (float) (pos.getWorldVar().y + r / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));
        float xDelta = cx - Math.max(aBox.pos.getWorldVar().x + (aBox.getWidth()/2), Math.min(cx,
                                     aBox.pos.getWorldVar().x));
        float yDelta = cy - Math.max(aBox.pos.getWorldVar().y + (aBox.getHeight()/2), Math.min(cy,
                aBox.pos.getWorldVar().y));

        if (xDelta * xDelta + yDelta * yDelta < (this.r / Math.sqrt(2)) * (this.r / Math.sqrt(2))){
            return true;
        }
        return false;
    }

    public boolean collisionTile(float dx, float dy){
        for (int c = 0; c < 4; c++){
            int xt = (int) ( (pos.x + dx) + (c % 2) * w + xOffset) / 64;
            int yt = (int) ( (pos.y + dy) + (int)(c / 2) * h + yOffset) / 64;

            if (TileMapNorm.blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))){
                Block block = TileMapNorm.blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                if (!(block instanceof NormBlock)) {
                    return collisionHole(dx, dy, xt, yt, block);
                }
                return block.update(this);
            }
        }
        return false;
    }

    public boolean collisionHole(float dx, float dy, float xt, float yt, Block block){
        int nextDx = (int) (( (pos.x + dx ) + xOffset) / 64 + w / 64);
        int nextDy = (int) (( (pos.y + dy ) + yOffset) / 64 + h / 64);

        if ((nextDy == yt + 1) || (nextDx == xt + 1)){
            if (TileMapObj.blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))){
                Block neighbBlock = TileMapObj.blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                return neighbBlock.update(this);
            } else {
                if (block.isInside(this)){
                    return block.update(this);
                }
            }
        }
        return false;
    }

}
