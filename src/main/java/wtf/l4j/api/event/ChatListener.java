package wtf.l4j.api.event;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.Getter;
import lombok.Setter;

public interface ChatListener {

    void onMessage(final ChatEvent chatEvent);

    final class ChatEvent extends CancellableEvent<ChatListener> {

        public static final int ID = 0;
        @Getter @Setter private String message;
        @Getter private Type type;

        public ChatEvent(final String message, Type type) {
            this.message = message;
            this.type = type;
        }

        //@formatter:off
        @Override
        public void call(ChatListener listener) {
            listener.onMessage(this);
        }
        //@formatter:on
    }
}
