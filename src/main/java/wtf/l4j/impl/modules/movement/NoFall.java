package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.newevent.PacketListener;
import wtf.l4j.api.newevent.Type;
import wtf.l4j.mixin.accessors.PlayerMoveC2SPacketAccessor;

@ModuleInfo(name = "NoFall", desc = "Never take fall damage again", category = Category.MOVEMENT)
public class NoFall extends Module implements PacketListener {

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
        if(packetEvent.getType() == Type.INCOMING) {
            if (packetEvent.getPacket() instanceof PlayerMoveC2SPacket packet) {
                if (!packet.isOnGround()) {
                    ((PlayerMoveC2SPacketAccessor) packet).setOnGround(true);
                }
            }
        }
    }
}
