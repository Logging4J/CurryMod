package wtf.l4j.api.newevent;

import de.florianmichael.dietrichevents2.CancellableEvent;

public interface GameTickListener {

    void onGameTick();

    final class GameTickEvent extends CancellableEvent<GameTickListener> {

        public static final int ID = 3;

        //@formatter:off
        @Override
        public void call(GameTickListener listener) {
            listener.onGameTick();
        }
        //@formatter:on
    }

}
