package wtf.l4j.impl.commands;

import wtf.l4j.api.command.Command;
import wtf.l4j.api.utils.text.TextUtil;

public class Emoji extends Command {

    @Override
    public void execute(String arguments) {
        String[] split = arguments.split(" ");
        String emoji = split[0].toLowerCase();
        switch (emoji) {
            case "shrug" -> mc.getNetworkHandler().sendChatMessage(TextUtil.SHRUG);
            case "lenny" -> mc.getNetworkHandler().sendChatMessage(TextUtil.LENNY);
            case "tableflip" -> mc.getNetworkHandler().sendChatMessage(TextUtil.TABLE_FLIP);
            default -> errorMessage("Invalid emoji");
        }
    }

}
