package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;

@Getter
@AllArgsConstructor
public class RenderHudEvent extends Event {

    private DrawContext context;
    private float tickDelta;

}
