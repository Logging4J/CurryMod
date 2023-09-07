package wtf.l4j.api.manager.managers;

import lombok.Getter;

import wtf.l4j.api.command.Command;
import wtf.l4j.api.utils.text.ChatHelper;
import wtf.l4j.impl.commands.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager extends ChatHelper {

    private List<Command> commands;
    public static final String PREFIX = ";";

    public void init(){
        commands = new ArrayList<>();
        commands.add(new FakeGM());
        commands.add(new SilentLeave());
        commands.add(new StealSkin());
        commands.add(new UUID());
        commands.add(new Emoji());
    }

    public void runCommand(String args) {
        boolean found = false;
        String[] split = args.split(" ");
        String startCommand = split[0];
        String arguments = args.substring(startCommand.length()).trim();
        for (Command command : commands) {
            if (startCommand.equals(PREFIX + command.getAlias())) {
                command.execute(arguments);
                found = true;
            }
        }
        if (!found) {
            errorMessage("Unknown command");
        }
    }
}
