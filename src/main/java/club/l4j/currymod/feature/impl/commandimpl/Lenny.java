package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.util.TextUtil;

@Command.Construct(name = "Lenny", description = "lenny face :^)", alias = {"lenny"})
public class Lenny extends Command {

    @Override
    public void onTrigger(String arguments) {
        mc.getNetworkHandler().sendChatMessage(TextUtil.LENNY);
        super.onTrigger(arguments);
    }
}
