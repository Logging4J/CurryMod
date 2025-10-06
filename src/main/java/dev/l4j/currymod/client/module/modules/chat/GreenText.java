package dev.l4j.currymod.client.module.modules.chat;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.listener.IMessageSendListener;

import java.util.Arrays;

@Module.Info(name = "GreenText", description = ">oh my goyd", category = Module.Category.CHAT)
public class GreenText extends Module implements IMessageSendListener {

    private final String[] commonPrefixes = {
            "!", "@", "#", "%", "^", "&", "*", ";", ":", ".", ",", "~", "?"
    };

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(MessageSendEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(MessageSendEvent.ID, this);
    }

    @Override
    public void onSendMessage(MessageSendEvent event) {
        String message = event.getMessage();

        boolean isException = Arrays.stream(commonPrefixes)
                .anyMatch(message::startsWith);

        if (!isException) {
            event.setMessage("> " + event.getMessage());
        }
    }
}
