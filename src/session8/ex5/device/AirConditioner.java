package session8.ex5.device;

import session8.ex5.observer.Observer;

public class AirConditioner implements Observer {

    private int temperature = 25;

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    @Override
    public void update(int temp) {
        if (temp > 30) {
            System.out.println("Điều hòa: Giữ nhiệt độ ổn định = " + temperature);
        }
    }

    public void showStatus() {
        System.out.println("Điều hòa hiện tại: " + temperature);
    }
}