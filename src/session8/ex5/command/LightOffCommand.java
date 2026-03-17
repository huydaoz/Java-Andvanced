package session8.ex5.command;

import session8.ex5.device.Light;

public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        System.out.println("SleepMode: Tắt đèn");
        light.turnOff();
    }
}