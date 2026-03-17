package session8.ex5.observer;

public interface Subject {
    void attach(Observer o);
    void notifyObservers();
}