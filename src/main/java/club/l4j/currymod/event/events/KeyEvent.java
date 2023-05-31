package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;

public class KeyEvent extends Event {

    private int key;
    private int action;

    public KeyEvent(int key, int action) {
        this.key = key;
        this.action = action;
    }

    public int getKey() {
        return key;
    }

    public int getAction() {
        return action;
    }
}
