package session8.ex5.command;

import java.util.List;

public class SleepModeCommand implements Command {

    private List<Command> commands;

    public SleepModeCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command cmd : commands) {
            cmd.execute();
        }
    }
}