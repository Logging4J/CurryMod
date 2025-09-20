package dev.l4j.currymod.listener;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.network.packet.Packet;

public interface IPacketSendListener {

    void onPacketSend(PacketSendEvent event);

    @Getter
    @AllArgsConstructor
    class PacketSendEvent extends CancellableEvent<IPacketSendListener> {

        public static final int ID = 4;

        private Packet<?> packet;

        @Override
        public void call(IPacketSendListener listener) {
            listener.onPacketSend(this);
        }
    }
}