package session8.ex1.device;

public class AirConditioner implements Device {

    @Override
    public void turnOn() {
        System.out.println("Điều hòa: Đang làm mát.");
    }

    @Override
    public void turnOff() {
        System.out.println("Điều hòa: Tắt.");
    }
}