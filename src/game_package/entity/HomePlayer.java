package game_package.entity;

import game_package.util.AABB;
import game_package.util.Vector2f;

import java.util.LinkedList;
import java.util.List;

public class HomePlayer {
    private List<Vector2f> coordinates = new LinkedList<>();
    protected AABB bounds;
    private int maxHp;
    private int curHp;

    public void setFirstCoordinate(Vector2f firstPos) {
        this.coordinates.add(firstPos);
    }

    public void setSecondCoordinate(Vector2f secondPos){
        if (!this.coordinates.get(0).equalsEpsilon(secondPos)) {
            this.coordinates.add(secondPos);
            this.bounds = new AABB(this.coordinates.get(0),
                                   this.coordinates.get(1));
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

    public void getHitted(Enemy enemy){
        this.curHp -= enemy.getPower();
        if (this.curHp < 0) { this.curHp = 0; }
    }
    public boolean homeIsBroke() { return this.curHp > 0; }
    public AABB getBounds() { return this.bounds; }
}

