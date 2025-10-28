package dev.l4j.currymod.client.module.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.listener.IMessageSendListener;
import lombok.Getter;

import java.util.Arrays;

@Module.Info(name = "ChatModifier", description = "Modify Chat", category = Module.Category.MISC)
public class ChatModifier extends Module implements IMessageSendListener {

    private final OptionBoolean greenText = new OptionBoolean("GreenText", true);

    @Getter
    private final OptionBoolean clearChat = new OptionBoolean("ClearChat", true);

    @Getter
    private final OptionBoolean keepHistory = new OptionBoolean("KeepHistory", true);

    public ChatModifier() {
        addOptions(greenText, clearChat, keepHistory);
    }

    private final String[] commonPrefixes = {
            "!", "@", "#", "%", "^", "&", "*", ";", ":", ".", ",", "~", "?", "'"
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
        if (!greenText.getValue()) return;

        String message = event.getMessage();

        boolean isException = Arrays.stream(commonPrefixes)
                .anyMatch(message::startsWith);

        if (!isException) {
            event.setMessage("> " + event.getMessage());
        }
    }
}
