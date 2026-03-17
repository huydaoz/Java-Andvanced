package session8.ex5.command;

import session8.ex5.device.AirConditioner;

public class ACSetTempCommand implements Command {

    private AirConditioner ac;
    private int temp;

    public ACSetTempCommand(AirConditioner ac, int temp) {
        this.ac = ac;
        this.temp = temp;
    }

    @Override
    public void execute() {
        System.out.println("SleepMode: Điều hòa set " + temp + "°C");
        ac.setTemperature(temp);
    }
}