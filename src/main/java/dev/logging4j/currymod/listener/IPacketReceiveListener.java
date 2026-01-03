package dev.logging4j.currymod.listener;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.network.packet.Packet;

public interface IPacketReceiveListener {

    void onPacketReceive(PacketReceiveEvent event);

    @Getter
    @AllArgsConstructor
    class PacketReceiveEvent extends CancellableEvent<IPacketReceiveListener> {

        public static final int ID = 3;

        private Packet<?> packet;

        @Override
        public void call(IPacketReceiveListener listener) {
            listener.onPacketReceive(this);
        }
    }
}
