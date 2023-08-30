package wtf.l4j.api.event;

import de.florianmichael.dietrichevents2.CancellableEvent;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.packet.Packet;

public interface PacketListener {

    void onPacket(final PacketEvent packetEvent);

    final class PacketEvent extends CancellableEvent<PacketListener> {

        public static final int ID = 1;
        @Getter @Setter private Packet<?> packet;
        @Getter private Type type;

        public PacketEvent(final Packet<?> packet, Type type) {
            this.packet = packet;
            this.type = type;
        }

        //@formatter:off
        @Override
        public void call(PacketListener listener) {
            listener.onPacket(this);
        }
        //@formatter:on
    }

}
