package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.util.TextUtil;
import net.minecraft.world.GameMode;

@Command.Construct(name = "emoji", description = "Prints an emoji", alias = {"emoji"}, usage = "emoji <emoji>")
public class Emoji extends Command {

    @Override
    public void onTrigger(String arguments) {
        if(!arguments.isEmpty()){
            String[] split = arguments.split(" ");
            String emoji = split[0].toLowerCase();
            switch (emoji) {
                case "shrug":
                    mc.getNetworkHandler().sendChatMessage(TextUtil.SHRUG);
                    break;
                case "lenny":
                    mc.getNetworkHandler().sendChatMessage(TextUtil.LENNY);
                    break;
                default:
                    sendMsg("Invalid emoji");
            }
        }else {
            sendMsg("Usage: " + getUsage());
        }
        super.onTrigger(arguments);
    }
}
