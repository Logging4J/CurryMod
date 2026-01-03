package dev.logging4j.currymod.listener;

import de.florianmichael.dietrichevents2.AbstractEvent;

public interface IPostPlayerTickListener {
    void onPostPlayerTick();

    class PostPlayerTickEvent implements AbstractEvent<IPostPlayerTickListener> {

        public static final int ID = 5;

        @Override
        public void call(IPostPlayerTickListener listener) {
            listener.onPostPlayerTick();
        }
    }
}
