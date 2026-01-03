package dev.logging4j.currymod.listener;

import de.florianmichael.dietrichevents2.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface IGameJoinListener {
    void onGameJoin(GameJoinEvent event);

    @Getter
    @AllArgsConstructor
    class GameJoinEvent implements AbstractEvent<IGameJoinListener> {

        public static final int ID = 8;

        @Override
        public void call(IGameJoinListener listener) {
            listener.onGameJoin(this);
        }
    }
}
