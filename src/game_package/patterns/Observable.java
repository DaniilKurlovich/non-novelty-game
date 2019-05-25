package game_package.patterns;

public interface Observable {
    void registerObserver(String name, Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
