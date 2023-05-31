package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.core.Command;

import java.util.Arrays;

@Command.Construct(name = "Help", description = "Help command", alias = {"help"})
public class Help extends Command {

    @Override
    public void onTrigger(String arguments) {
        for(Command c : CurryMod.featureManager.commands){
            Command.sendMsg("Name: " + c.getName());
            Command.sendMsg("Description: " + c.getDescription());
            Command.sendMsg("Alias: " + Arrays.toString(c.getAlias()));
            Command.sendMsg(" ");
        }
        super.onTrigger(arguments);
    }
}
