package wtf.l4j.api.event;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.Getter;
import lombok.Setter;

public interface DismountListener {

    void onDismount();

    final class DismountEvent extends CancellableEvent<DismountListener> {

        public static final int ID = 2;

        //@formatter:off
        @Override
        public void call(DismountListener listener) {
            listener.onDismount();
        }
        //@formatter:on
    }
}