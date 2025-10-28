package dev.l4j.currymod.listener;

import de.florianmichael.dietrichevents2.AbstractEvent;

public interface IGameTickListener {

    void onGameTick();

    class GameTickEvent extends AbstractEvent<IGameTickListener> {

        public static final int ID = 3;

        @Override
        public void call(IGameTickListener listener) {
            listener.onGameTick();
        }
    }
}
