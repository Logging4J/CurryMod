package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;

@Getter
public class Render2DEvent extends Event {

    private DrawContext context;
    private float tickDelta;

    public Render2DEvent(DrawContext context, float tickDelta) {
        this.context = context;
        this.tickDelta = tickDelta;
    }
}
