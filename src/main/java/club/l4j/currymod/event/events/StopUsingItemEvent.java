package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import lombok.Getter;
import net.minecraft.item.Item;

@Getter
public class StopUsingItemEvent extends Event {

    private Item item;

    public StopUsingItemEvent(Item item) {
        this.item = item;
    }

}
