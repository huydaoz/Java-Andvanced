package session8.ex3.command;

import session8.ex3.device.AirConditioner;

public class ACSetTemperatureCommand implements Command {

    private AirConditioner ac;
    private int newTemp;
    private int prevTemp;

    public ACSetTemperatureCommand(AirConditioner ac, int newTemp) {
        this.ac = ac;
        this.newTemp = newTemp;
    }

    @Override
    public void execute() {
        prevTemp = ac.getTemperature(); // lưu trạng thái cũ
        ac.setTemperature(newTemp);
    }

    @Override
    public void undo() {
        ac.setTemperature(prevTemp); // quay lại
        System.out.println("Undo: Điều hòa quay lại " + prevTemp);
    }
}