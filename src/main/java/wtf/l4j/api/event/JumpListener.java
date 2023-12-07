package wtf.l4j.api.event;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.Getter;
import lombok.Setter;

public interface JumpListener {

    void onJump();

    final class JumpEvent extends CancellableEvent<JumpListener> {

        public static final int ID = 6;
        @Getter
        @Setter
        private String message;
        @Getter private Type type;

        //@formatter:off
        @Override
        public void call(JumpListener listener) {
            listener.onJump();

        }
        //@formatter:on
    }

}
