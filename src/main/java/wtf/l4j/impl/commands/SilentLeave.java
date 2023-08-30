package wtf.l4j.impl.commands;

import net.minecraft.client.gui.screen.TitleScreen;
import wtf.l4j.api.command.Command;
import wtf.l4j.api.command.CommandInfo;

@CommandInfo(name = "SilentLeave", desc = "Leaves server client sided", alais = "silentleave")
public class SilentLeave extends Command {

    @Override
    public void execute(String arguments) {
        //Accidentally Found this trying to make an AutoLog lmao
        mc.disconnect(new TitleScreen());
    }
}
