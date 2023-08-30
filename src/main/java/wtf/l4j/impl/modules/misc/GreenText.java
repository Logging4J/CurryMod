package wtf.l4j.impl.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.newevent.ChatListener;

@ModuleInfo(name = "GreenText", desc = "send green chat messages on some servers", category = Category.MISC)
public class GreenText extends Module implements ChatListener {

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
        String s = chatEvent.getMessage();
        chatEvent.setMessage(">" + s);
    }
}
