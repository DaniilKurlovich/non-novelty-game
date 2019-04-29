package game_package.patterns;
import game_package.util.Vector2f;

import java.util.LinkedList;
import java.util.List;

/*
        positionData.setPosition(new Vector2f(21, 21));
        positionData.setPosition(new Vector2f(12, 12));
}
*/

abstract class Observer {
    void update(){}
    void update(Vector2f pos){}
}


interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

public class PositionData implements Observable {
    private List<Observer> observers;           // слушатели
    private Vector2f position;

    public PositionData() {
        observers = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(position);
    }

    public void setPosition(Vector2f position) {
        this.position = position;
        notifyObservers();
    }
}

class CurrentConditionsDisplay extends Observer {
    private PositionData positionData;
    private Vector2f position;

    public CurrentConditionsDisplay(PositionData positionData) {
        this.positionData = positionData;
        positionData.registerObserver(this);
    }

    @Override
    public void update(Vector2f position) {
        this.position = position;
        display();
    }

    public void display() {
        System.out.printf(String.format("Перемещение на %s", position) + '\n');
    }
}