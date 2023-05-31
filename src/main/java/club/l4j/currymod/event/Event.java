package club.l4j.currymod.event;

import demo.knight.demobus.event.IDemoVent;

public class Event implements IDemoVent {

    private boolean canceled = false;


    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }
}
