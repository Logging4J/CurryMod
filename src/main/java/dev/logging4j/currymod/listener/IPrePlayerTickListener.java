package dev.logging4j.currymod.listener;

import de.florianmichael.dietrichevents2.AbstractEvent;

public interface IPrePlayerTickListener {
    void onPrePlayerTick();

    class PrePlayerTickEvent implements AbstractEvent<IPrePlayerTickListener> {

        public static final int ID = 6;

        @Override
        public void call(IPrePlayerTickListener listener) {
            listener.onPrePlayerTick();
        }
    }
}
