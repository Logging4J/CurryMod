package club.l4j.currymod.impl.hacks.combat;

import club.l4j.currymod.core.event.events.PacketSendEvent;
import club.l4j.currymod.core.hack.Hack;
import demo.knight.demobus.event.DemoListen;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Hack.Construct(name = "Crits", description = "Hit harder dadi", category = Hack.Category.COMBAT)
public class Crits extends Hack {

    @DemoListen
    public void onPacketSend(PacketSendEvent e){
        if(e.getPacket() instanceof PlayerInteractEntityC2SPacket p){
            if(getEntity(p) instanceof LivingEntity){
                sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY() + 0.0625, mc.player.getZ(), false));
                sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY(), mc.player.getZ(), false));
            }
        }
    }

    //Fuck You Mojang Why u make me do this
    public Entity getEntity(PlayerInteractEntityC2SPacket p){
        PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
        p.write(packetByteBuf);
        return mc.world.getEntityById(packetByteBuf.readVarInt());
    }

}
