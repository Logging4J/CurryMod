package wtf.l4j.impl.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.event.ChatListener;

@ModuleInfo(name = "ChatModifier", desc = "Modify Chat", category = Category.MISC)
public class ChatSuffix extends Module implements ChatListener {

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(ChatEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(ChatEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onMessage(ChatEvent chatEvent) {
        String message = chatEvent.getMessage();
        chatEvent.setMessage(message += " [卍]");
    }
}
