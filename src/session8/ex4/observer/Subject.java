package session8.ex4.observer;

public interface Subject {

    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}