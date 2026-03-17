package session8.ex5.device;

import session8.ex5.observer.Observer;

public class Fan implements Observer {

    private String speed = "OFF";

    public void setLow() {
        speed = "LOW";
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    @Override
    public void update(int temperature) {
        if (temperature > 30) {
            speed = "HIGH";
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
        }
    }

    public void showStatus() {
        System.out.println("Quạt hiện tại: " + speed);
    }
}