package session9.thuchanh;

public interface TrafficLightState {
    TrafficLightState next();
    String color();
}
