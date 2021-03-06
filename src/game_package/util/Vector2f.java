package game_package.util;


import java.util.*;

public class Vector2f {

    public float x;
    public float y;

    public static float worldX;
    public static float worldY;

    private float firstX;
    private float firstY;

    private double epsilonCompare = 0.1;

    public Vector2f(){
        x = 0;
        y = 0;
    }

    public Vector2f(Vector2f v){
        new Vector2f(v.x, v.y);
    }

    public void restoreCoordinates(){
        x = firstX;
        y = firstY;
    }

    public Vector2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void addX(float i) { x += i; }
    public void addY(float i) { y += i; }

    public void setX(float i) { x = i; }
    public void setY(float i) { y = i; }

    public void setVector(Vector2f v) {
        setVector(v.x, v.y);
    }

    public void setVector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public static void setWorldVar(float x, float y){
        worldX = x;
        worldY = y;
    }

    public Vector2f getWorldVar(){
        return new Vector2f(x - worldX, y - worldY);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

    public void setEpsilon(double epsilon) { this.epsilonCompare = epsilon; }

    public boolean equalsEpsilon(Vector2f other){
        return ((other.x < this.x + epsilonCompare) && (other.x > this.x - epsilonCompare) &&
               (other.y < this.y + epsilonCompare) && (other.y > this.y - epsilonCompare));
    }

    public List compareAndGetDelta(Vector2f other){
        ArrayList list = new ArrayList();
        System.out.println(this);
        System.out.println(other);
        if ((this.x > other.x) && (this.y > other.y)){
            list.add(new Vector2f(other.x, other.y));
            list.add((int)this.x - other.x);
            list.add((int)this.y - other.y);
            return list;
        } else {
            list.add(new Vector2f(this.x, this.y));
            list.add((int)Math.abs(this.x - other.x));
            list.add((int)Math.abs(this.y - other.y));
            System.out.println(list.toString());
            return list;
        }
    }

}
