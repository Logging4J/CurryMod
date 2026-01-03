package dev.logging4j.currymod.listener;

import de.florianmichael.dietrichevents2.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public interface IRender2DListener {
    void onRender2D(Render2DEvent event);

    @Getter
    @AllArgsConstructor
    class Render2DEvent implements AbstractEvent<IRender2DListener> {

        public static final int ID = 7;

        private DrawContext context;
        private RenderTickCounter renderTickCounter;

        @Override
        public void call(IRender2DListener listener) {
            listener.onRender2D(this);
        }
    }
}
