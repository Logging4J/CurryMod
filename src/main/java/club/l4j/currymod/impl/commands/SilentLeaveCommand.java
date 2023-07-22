package club.l4j.currymod.impl.commands;

import club.l4j.currymod.core.command.Command;
import net.minecraft.client.gui.screen.TitleScreen;

@Command.Construct(name = "SilentLeave", description = "Silently leave the server", alias = {"silentleave"}, usage = "silentleave")
public class SilentLeaveCommand extends Command {

    @Override
    public void onTrigger(String arguments) {
        mc.disconnect(new TitleScreen());
        super.onTrigger(arguments);
    }
}
