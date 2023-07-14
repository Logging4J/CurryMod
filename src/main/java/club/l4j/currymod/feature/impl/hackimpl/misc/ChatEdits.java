package club.l4j.currymod.feature.impl.hackimpl.misc;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;

@Hack.Construct(name = "ChatEdits", description = "chat edits", category = Hack.Category.MISC)
public class ChatEdits extends Hack {

    public static OptionBoolean history = new OptionBoolean("InfChatHistory", true);

    public ChatEdits(){
        addOptions(history);
    }

}
