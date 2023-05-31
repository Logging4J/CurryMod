package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class Render2DEvent extends Event {

    private MatrixStack matrixStack;
    private float tickDelta;

    public Render2DEvent(MatrixStack matrixStack, float tickDelta) {
        this.matrixStack = matrixStack;
        this.tickDelta = tickDelta;
    }

    public MatrixStack getMatrixStack() {
        return matrixStack;
    }

    public float getTickDelta() {
        return tickDelta;
    }
}
