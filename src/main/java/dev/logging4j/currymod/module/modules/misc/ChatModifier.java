package dev.logging4j.currymod.module.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.listener.IMessageReceiveListener;
import dev.logging4j.currymod.listener.IMessageSendListener;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.options.OptionBoolean;
import lombok.Getter;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Module.Info(name = "ChatModifier", description = "Chat but better", category = Module.Category.MISC)
public class ChatModifier extends Module implements IMessageSendListener, IMessageReceiveListener {

    private final OptionBoolean greenText = new OptionBoolean("GreenText", true);
    private final OptionBoolean suffix = new OptionBoolean("Suffix", true);
    private final OptionBoolean timestamps = new OptionBoolean("Timestamps", true);

    @Getter
    private final OptionBoolean clearChat = new OptionBoolean("ClearChat", true);

    @Getter
    private final OptionBoolean keepHistory = new OptionBoolean("KeepHistory", true);

    public ChatModifier() {
        addOptions(greenText, suffix, timestamps, clearChat, keepHistory);
    }

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final String[] commonPrefixes = {
            "!", "@", "#", "%", "^", "&", "*", ";", ":", ".", ",", "~", "?", "'"
    };

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(MessageSendEvent.ID, this);
        DietrichEvents2.global().subscribe(MessageReceiveEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(MessageSendEvent.ID, this);
        DietrichEvents2.global().unsubscribe(MessageReceiveEvent.ID, this);
    }

    @Override
    public void onSendMessage(MessageSendEvent event) {
        String message = event.getMessage();

        boolean isException = Arrays.stream(commonPrefixes)
                .anyMatch(message::startsWith);

        if (isException) return;

        if (!greenText.getValue()) return;

        event.setMessage("> " + event.getMessage());

        if (!suffix.getValue()) return;

        event.setMessage(event.getMessage() + " [ᛋᛋ]");
    }

    @Override
    public void onReceiveMessage(MessageReceiveEvent event) {
        Text timestamp = Text.literal(Formatting.WHITE + "<" + Formatting.GREEN + dateFormat.format(new Date()) + Formatting.WHITE + "> ");

        Text text = event.getMessage();

        if (timestamps.getValue()) {
            text = Text.empty().append(timestamp).append(text);
        }

        event.setMessage(text);
    }
}
