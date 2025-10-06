package dev.l4j.currymod.listener;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.text.Text;

public interface IMessageReceiveListener {

    void onReceiveMessage(MessageReceiveEvent event);

    @Getter
    class MessageReceiveEvent extends CancellableEvent<IMessageReceiveListener> {

        public static final int ID = 7;

        private Text message;
        private boolean modified;

        public MessageReceiveEvent(Text message) {
            this.message = message;
            this.modified = false;
        }

        public void setMessage(Text message) {
            this.message = message;
            this.modified = true;
        }

        @Override
        public void call(IMessageReceiveListener listener) {
            listener.onReceiveMessage(this);
        }
    }

}
