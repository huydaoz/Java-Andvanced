package session8.ex5.command;

import session8.ex5.device.Fan;

public class FanLowCommand implements Command {

    private Fan fan;

    public FanLowCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        System.out.println("SleepMode: Quạt tốc độ thấp");
        fan.setLow();
    }
}