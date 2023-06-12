package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import lombok.Getter;
import net.minecraft.network.packet.Packet;

@Getter
public class PacketReceiveEvent extends Event {
    private Packet<?> packet;

    public PacketReceiveEvent(Packet<?> packet) {
        this.packet = packet;
    }

}
