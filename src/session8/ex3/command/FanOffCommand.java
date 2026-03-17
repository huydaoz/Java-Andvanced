package session8.ex3.command;

import session8.ex3.device.Fan;

public class FanOffCommand implements Command {

    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOff();
    }

    @Override
    public void undo() {
        fan.turnOn();
    }
}