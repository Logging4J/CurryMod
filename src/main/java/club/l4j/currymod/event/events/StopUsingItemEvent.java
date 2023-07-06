package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.item.Item;

@Getter
@AllArgsConstructor
public class StopUsingItemEvent extends Event {

    private Item item;

}
