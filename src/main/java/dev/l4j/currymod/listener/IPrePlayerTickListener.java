package dev.l4j.currymod.listener;

import de.florianmichael.dietrichevents2.AbstractEvent;

public interface IPrePlayerTickListener {
    void onPrePlayerTick();

    class PrePlayerTickEvent extends AbstractEvent<IPrePlayerTickListener> {

        public static final int ID = 8;

        @Override
        public void call(IPrePlayerTickListener listener) {
            listener.onPrePlayerTick();
        }
    }
}
