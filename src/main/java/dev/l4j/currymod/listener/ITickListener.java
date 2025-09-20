package dev.l4j.currymod.listener;

import de.florianmichael.dietrichevents2.AbstractEvent;

public interface ITickListener {

    void onTick();

    class TickEvent extends AbstractEvent<ITickListener> {

        public static final int ID = 3;

        @Override
        public void call(ITickListener listener) {
            listener.onTick();
        }
    }
}
