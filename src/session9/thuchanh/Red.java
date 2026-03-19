package session9.thuchanh;

public class Red implements TrafficLightState{
    public TrafficLightState next() { return new Green(); }
    public String color() { return "RED"; }
}
