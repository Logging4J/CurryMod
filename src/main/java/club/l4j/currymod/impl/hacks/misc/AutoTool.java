package club.l4j.currymod.impl.hacks.misc;

import club.l4j.currymod.core.event.events.PacketSendEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.util.player.PlayerUtil;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;

@Hack.Construct(name = "AutoTool", description = "Auto select tools if breaking blocks", category = Hack.Category.MISC)
public class AutoTool extends Hack {

    @DemoListen
    public void onPacketSend(PacketSendEvent e){
        if(e.getPacket() instanceof PlayerActionC2SPacket p){
            if(p.getAction() == PlayerActionC2SPacket.Action.START_DESTROY_BLOCK){
                mc.player.getInventory().selectedSlot = PlayerUtil.getBestAvailableToolSlot(p.getPos());
                sendPacket(new UpdateSelectedSlotC2SPacket(mc.player.getInventory().selectedSlot));
            }
        }
    }
}
