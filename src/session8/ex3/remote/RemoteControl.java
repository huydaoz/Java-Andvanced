package session8.ex3.remote;

import session8.ex3.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RemoteControl {

    private Map<Integer, Command> buttons = new HashMap<>();
    private Stack<Command> history = new Stack<>();

    public void setCommand(int slot, Command command) {
        buttons.put(slot, command);
        System.out.println("Đã gán command cho nút " + slot);
    }

    public void pressButton(int slot) {
        Command command = buttons.get(slot);

        if (command != null) {
            command.execute();
            history.push(command);
        } else {
            System.out.println("Nút chưa được gán.");
        }
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
        } else {
            System.out.println("Không có lệnh để undo.");
        }
    }
}