package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.util.TextUtil;

@Command.Construct(name = "Emoji", description = "Prints an emoji", alias = {"emoji"}, usage = "emoji <emoji>")
public class Emoji extends Command {

    @Override
    public void onTrigger(String arguments) {
        if(!arguments.isEmpty()){
            String[] split = arguments.split(" ");
            String emoji = split[0].toLowerCase();
            switch (emoji) {
                case "shrug" -> mc.getNetworkHandler().sendChatMessage(TextUtil.SHRUG);
                case "lenny" -> mc.getNetworkHandler().sendChatMessage(TextUtil.LENNY);
                default -> sendMsg("Invalid emoji");
            }
        }else {
            sendMsg("Usage: " + getUsage());
        }
        super.onTrigger(arguments);
    }
}
