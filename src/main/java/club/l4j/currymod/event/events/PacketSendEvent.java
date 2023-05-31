package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import net.minecraft.network.packet.Packet;

public class PacketSendEvent extends Event {

    private Packet<?> packet;

    public PacketSendEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }

}
