package club.l4j.currymod.core.manager;

import club.l4j.currymod.core.command.Command;
import club.l4j.currymod.core.util.IGlobals;
import club.l4j.currymod.impl.commands.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements IGlobals {

    @Getter
    private List<Command> commands = new ArrayList<>();

    public CommandManager(){
        addCommand(new VClipCommand());
        addCommand(new SilentLeaveCommand());
        addCommand(new HelpCommand());
        addCommand(new FakePlayerCommand());
        addCommand(new GameModeCommand());
        addCommand(new EmojiCommand());
        addCommand(new NBTCommand());
        addCommand(new PrefixCommand());
    }

    public void runCommand(String args) {
        boolean found = false;
        String[] split = args.split(" ");
        String startCommand = split[0];
        String arguments = args.substring(startCommand.length()).trim();
        for (Command command : commands) {
            for (String alias : command.getAlias()) {
                if (startCommand.equals(command.getPrefix() + alias)) {
                    command.onTrigger(arguments);
                    found = true;
                }
            }
        }
        if (!found) {
            sendMsg("Unknown command");
        }
    }

    private void addCommand(Command command) {
        commands.add(command);
    }

}
