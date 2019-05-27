package game_package.entity;

import game_package.GameObserver.MainObserver;
import game_package.util.AABB;
import game_package.util.Vector2f;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HomePlayer {
    private List<Vector2f> coordinates = new LinkedList<>();
    protected AABB bounds;
    private int maxHp;
    private int curHp;

    private int w;
    private int h;

    public void setFirstCoordinate(Vector2f firstPos) {
        this.coordinates.add(firstPos);
    }

    public void setSecondCoordinate(Vector2f secondPos, int hp){
        if (!this.coordinates.get(0).equalsEpsilon(secondPos)) {
            this.coordinates.add(secondPos);
            if (this.coordinates.get(0).x > this.coordinates.get(1).x){
                Collections.reverse(this.coordinates);
            }
            this.bounds = new AABB(this.coordinates.get(0),
                                   this.coordinates.get(1));
            this.maxHp = hp;
            this.curHp = hp;
            w = (int)Math.abs(this.coordinates.get(0).x - this.coordinates.get(1).x);
            h = (int)Math.abs(this.coordinates.get(0).y - this.coordinates.get(1).y);
        }
    }

    public List<Vector2f> getCoordinates() { return this.coordinates; }

    public boolean isHomeInited() { return this.coordinates.size() == 2; }
    public void setMaxHp(int hp){ this.maxHp = hp; }
    public void setCurHp(int hp){ this.curHp = hp; }
    public void getRepair(int hp){
        this.curHp += hp;
        if (this.curHp > this.maxHp){
            this.curHp = this.maxHp;
        }
    }

    public void getFullRepair(){ this.curHp = maxHp; }

    public int getCurHp() { return this.curHp; }

    public void getHitted(Enemy enemy){
        this.curHp -= enemy.getPower();
        if (this.curHp < 0) { this.curHp = 0; }
    }
    public boolean homeIsBroke() { return this.curHp <= 0; }
    public AABB getBounds() { return this.bounds; }

    public void render(Graphics2D grphs){
        grphs.setColor(Color.red);
        grphs.drawRect((int)this.coordinates.get(0).getWorldVar().x, (int)this.coordinates.get(0).getWorldVar().y,
                        w, 6);
        grphs.setColor(Color.black);
        grphs.fillRect((int)this.coordinates.get(0).getWorldVar().x, (int)this.coordinates.get(0).getWorldVar().y - 1,
                        w, 7);
        grphs.setColor(Color.red);
        grphs.fillRect((int)this.coordinates.get(0).getWorldVar().x, (int)this.coordinates.get(0).getWorldVar().y,
                       (int)(w * ((double)this.curHp / (double)this.maxHp)), 5);
    }

    public int getHp() { return this.curHp; }

}

