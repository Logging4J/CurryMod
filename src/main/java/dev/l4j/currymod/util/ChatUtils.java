package dev.l4j.currymod.util;

import lombok.experimental.UtilityClass;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@UtilityClass
public class ChatUtils implements MinecraftInterface{

    public void sendErrorMessage(String message) {
        sendClientMessage(Formatting.RED + "[Error] " + Formatting.GRAY + message);
    }

    public int sendErrorMessage(String message, int status) {
        sendClientMessage(Formatting.RED + "[Error] " + Formatting.GRAY + message);
        return status;
    }

    public void sendClientMessage(String message) {
        if (mc.world == null || mc.player == null) return;

        mc.inGameHud.getChatHud().addMessage(Text.literal(getClientMessagePrefix() + Formatting.GRAY + message));
    }

    public int sendClientMessage(String message, int status) {
        sendClientMessage(message);
        return status;
    }

    private String getClientMessagePrefix() {
        return Formatting.WHITE + "[" + Formatting.GREEN + "CurryMod" + Formatting.WHITE + "] ";
    }
}
