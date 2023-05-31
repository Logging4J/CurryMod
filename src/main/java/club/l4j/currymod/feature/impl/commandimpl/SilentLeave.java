package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import net.minecraft.client.gui.screen.TitleScreen;

@Command.Construct(name = "SilentLeave", description = "Silently leave the server", alias = {"silentleave"})
public class SilentLeave extends Command {

    @Override
    public void onTrigger(String arguments) {
        mc.disconnect(new TitleScreen());
        super.onTrigger(arguments);
    }
}
