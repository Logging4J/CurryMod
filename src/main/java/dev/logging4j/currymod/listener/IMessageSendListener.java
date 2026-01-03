package dev.logging4j.currymod.listener;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public interface IMessageSendListener {

    void onSendMessage(MessageSendEvent event);

    @Setter
    @Getter
    @AllArgsConstructor
    class MessageSendEvent extends CancellableEvent<IMessageSendListener> {

        public static final int ID = 2;

        private String message;

        @Override
        public void call(IMessageSendListener listener) {
            listener.onSendMessage(this);
        }
    }
}
