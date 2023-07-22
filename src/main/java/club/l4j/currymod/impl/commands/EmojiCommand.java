package club.l4j.currymod.impl.commands;

import club.l4j.currymod.core.command.Command;
import club.l4j.currymod.core.util.TextUtil;

@Command.Construct(name = "Emoji", description = "Prints an emoji", alias = {"emoji"}, usage = "emoji <emoji>")
public class EmojiCommand extends Command {

    @Override
    public void onTrigger(String arguments) {
        if(!arguments.isEmpty()){
            String[] split = arguments.split(" ");
            String emoji = split[0].toLowerCase();
            switch (emoji) {
                case "shrug" -> mc.getNetworkHandler().sendChatMessage(TextUtil.SHRUG);
                case "lenny" -> mc.getNetworkHandler().sendChatMessage(TextUtil.LENNY);
                case "tableflip" -> mc.getNetworkHandler().sendChatMessage(TextUtil.TABLE_FLIP);
                case "angry" -> mc.getNetworkHandler().sendChatMessage(TextUtil.ANGRY);
                case "happy" -> mc.getNetworkHandler().sendChatMessage(TextUtil.HAPPY);

                default -> sendMsg("Invalid emoji");
            }
        }else {
            sendMsg("Usage: " + getUsage());
        }
        super.onTrigger(arguments);
    }
}
