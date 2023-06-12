package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import lombok.Getter;

@Getter
public class KeyEvent extends Event {

    private int key;
    private int action;

    public KeyEvent(int key, int action) {
        this.key = key;
        this.action = action;
    }
}
