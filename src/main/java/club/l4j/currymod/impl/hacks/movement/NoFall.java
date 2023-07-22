package club.l4j.currymod.impl.hacks.movement;

import club.l4j.currymod.core.event.events.PacketSendEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.mixin.minecraft.IPlayerMoveC2SPacket;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Hack.Construct(name = "NoFall", description = "fuck fall damage", category = Hack.Category.MOVEMENT)
public class NoFall extends Hack {

    @DemoListen
    public void onPacketSend(PacketSendEvent e) {
        if(e.getPacket() instanceof PlayerMoveC2SPacket packet){
            if(!packet.isOnGround()) {
                ((IPlayerMoveC2SPacket) packet).setOnGround(true);
            }
        }
    }
}
