package dev.l4j.currymod.listener;

import de.florianmichael.dietrichevents2.AbstractEvent;

public interface IPostPlayerTickListener {
    void onPostPlayerTick();

    class PostPlayerTickEvent extends AbstractEvent<IPostPlayerTickListener> {

        public static final int ID = 9;

        @Override
        public void call(IPostPlayerTickListener listener) {
            listener.onPostPlayerTick();
        }
    }
}
