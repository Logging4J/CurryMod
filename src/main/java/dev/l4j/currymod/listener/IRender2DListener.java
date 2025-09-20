package dev.l4j.currymod.listener;

import de.florianmichael.dietrichevents2.CancellableEvent;
import dev.l4j.currymod.util.RenderUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;

public interface IRender2DListener {
    void onRender2D(Render2DEvent event);

    @Getter
    @AllArgsConstructor
    class Render2DEvent extends CancellableEvent<IRender2DListener> {

        public static final int ID = 2;

        private DrawContext context;
        public int screenWidth, screenHeight;
        public double frameTime;
        public float tickDelta;

        public Render2DEvent(DrawContext context, int screenWidth, int screenHeight, float tickDelta) {
            this.context = context;
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
            this.frameTime = RenderUtils.frameTime;
            this.tickDelta = tickDelta;
        }

        @Override
        public void call(IRender2DListener listener) {
            listener.onRender2D(this);
        }
    }
}
