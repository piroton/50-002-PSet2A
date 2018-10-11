package c.piroton.testbed;

public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers();
}
