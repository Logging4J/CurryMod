package club.l4j.currymod.impl.commands;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.command.Command;

import java.util.Arrays;

@Command.Construct(name = "Help", description = "Help command", alias = {"help"}, usage = "help")
public class HelpCommand extends Command {

    @Override
    public void onTrigger(String arguments) {
        for(Command c : CurryMod.commandManager.getCommands()){
            sendMsg("Name: " + c.getName());
            sendMsg("Description: " + c.getDescription());
            sendMsg("Usage: " + c.getUsage());
            sendMsg("Alias: " + Arrays.toString(c.getAlias()));
            sendMsg(" ");
        }
        super.onTrigger(arguments);
    }
}
