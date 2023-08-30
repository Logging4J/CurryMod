package wtf.l4j.impl.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.event.Type;
import wtf.l4j.api.utils.ItemUtils;

@ModuleInfo(name = "AutoTool", desc = "Best tool for block being broken", category = Category.MISC)
public class AutoTool extends Module implements PacketListener {

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(PacketEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(PacketEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onPacket(PacketEvent packetEvent) {
        if (packetEvent.getType() == Type.OUTGOING) {
            if (packetEvent.getPacket() instanceof PlayerActionC2SPacket packet) {
                if (packet.getAction() == PlayerActionC2SPacket.Action.START_DESTROY_BLOCK) {
                    mc.player.getInventory().selectedSlot = ItemUtils.getBestAvailableToolSlot(packet.getPos());
                    sendPacket(new UpdateSelectedSlotC2SPacket(mc.player.getInventory().selectedSlot));
                }
            }
        }
    }
}
