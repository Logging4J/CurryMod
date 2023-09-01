package wtf.l4j.api.event;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.packet.Packet;

public interface WorldRenderListener {

    void onRenderWorld(final RenderWorldEvent event);

    final class RenderWorldEvent extends CancellableEvent<WorldRenderListener> {

        public static final int ID = 5;

        @Getter private MatrixStack stack;

        public RenderWorldEvent(MatrixStack stack) {
            this.stack = stack;
        }

        //@formatter:off
        @Override
        public void call(WorldRenderListener listener) {
            listener.onRenderWorld(this);
        }
        //@formatter:on
    }
}
