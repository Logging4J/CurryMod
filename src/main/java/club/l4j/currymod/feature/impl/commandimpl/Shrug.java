package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.util.TextUtil;

@Command.Construct(name = "Shrug", description = "Shrug", alias = {"shrug"})
public class Shrug extends Command {

    @Override
    public void onTrigger(String arguments) {
        mc.getNetworkHandler().sendChatMessage(TextUtil.SHRUG);
        super.onTrigger(arguments);
    }
}
