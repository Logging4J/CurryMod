package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.network.packet.Packet;

@Getter
@AllArgsConstructor
public class PacketReceiveEvent extends Event {
    private Packet<?> packet;
}
