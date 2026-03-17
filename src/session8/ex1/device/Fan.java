package session8.ex1.device;

public class Fan implements Device {

    @Override
    public void turnOn() {
        System.out.println("Quạt: Đang quay.");
    }

    @Override
    public void turnOff() {
        System.out.println("Quạt: Dừng.");
    }
}