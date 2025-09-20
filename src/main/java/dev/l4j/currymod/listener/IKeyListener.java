package dev.l4j.currymod.listener;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface IKeyListener {

    void onKey(KeyEvent event);

    @Getter
    @AllArgsConstructor
    class KeyEvent extends CancellableEvent<IKeyListener> {

        public static final int ID = 1;

        private int key, action;

        @Override
        public void call(IKeyListener listener) {
            listener.onKey(this);
        }
    }
}
