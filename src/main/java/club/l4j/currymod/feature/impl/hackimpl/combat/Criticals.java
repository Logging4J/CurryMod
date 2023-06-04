package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.feature.core.Hack;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.network.packet.Packet;

@Hack.Construct(name = "Criticals", description = "CRITZZ", category = Hack.Category.COMBAT)
public class Criticals extends Hack {

    DemoListen
    public void onSendPacket(Packet<?> packet) {
        //if (packet instanceof )
    }
}
