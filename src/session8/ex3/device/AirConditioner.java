package session8.ex3.device;

public class AirConditioner {

    private int temperature = 25;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Điều hòa: Nhiệt độ = " + temperature);
    }

    public int getTemperature() {
        return temperature;
    }
}