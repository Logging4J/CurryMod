package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import net.minecraft.item.Item;

public class StopUsingItemEvent extends Event {

    private Item item;

    public StopUsingItemEvent(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
