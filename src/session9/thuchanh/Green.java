package session9.thuchanh;

public class Green implements TrafficLightState{
    public TrafficLightState next() { return new Yellow(); }
    public String color() { return "GREEN"; }
}
