package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.core.Command;

import java.util.Arrays;

@Command.Construct(name = "Help", description = "Help command", alias = {"help"})
public class Help extends Command {

    @Override
    public void onTrigger(String arguments) {
        for(Command c : CurryMod.featureManager.commands){
            sendMsg("Name: " + c.getName());
            sendMsg("Description: " + c.getDescription());
            sendMsg("Alias: " + Arrays.toString(c.getAlias()));
            sendMsg(" ");
        }
        super.onTrigger(arguments);
    }
}
