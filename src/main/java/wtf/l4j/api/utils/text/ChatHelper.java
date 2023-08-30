package wtf.l4j.api.utils.text;

import net.minecraft.text.Text;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.utils.IGlobals;

import static wtf.l4j.api.utils.text.TextUtil.*;

public class ChatHelper implements IGlobals {

    public static void playerMsg(String message) {
        mc.inGameHud.getChatHud().addToMessageHistory(message);
        mc.player.networkHandler.sendChatMessage(message);
    }

    public void basicMessage(String text){
        mc.inGameHud.getChatHud().addToMessageHistory(GRAY + "[" + PURPLE +"CurryMod" + GRAY + "] " + WHITE + text);
    }

    public void toggleMessage(Module module){
        String s = module.isEnabled() ? GREEN + "ON" : RED + "OFF";
        basicMessage("Toggled " + module.getName() + GRAY +" [" + s + GRAY +"]");
    }

    public void errorMessage(String s){
        basicMessage(GRAY + "[" + RED + "ERROR" + GRAY + "] "+ WHITE + s);
    }

}
