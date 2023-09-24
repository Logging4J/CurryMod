package wtf.l4j.impl.modules.combat;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

import net.minecraft.network.packet.s2c.play.PlayPingS2CPacket;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.event.Type;
import wtf.l4j.api.module.option.options.OptionMode;

@ModuleInfo(name = "Velocity", desc = "Modify velocity", category = Category.COMBAT)
public class Velocity extends Module implements PacketListener {

    private OptionMode mode = new OptionMode("Mode", "Vanilla", "Vanilla", "OldGrim");
    public static OptionBoolean push = new OptionBoolean("NoPush", true);

    private int grimTicks;

    public Velocity(){
        addOptions(push, mode);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(PacketListener.PacketEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(PacketListener.PacketEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onPacket(PacketEvent packetEvent) {
        if(packetEvent.getType() ==  Type.INCOMING) {
            if (packetEvent.getPacket() instanceof EntityVelocityUpdateS2CPacket packet) {
                if (packet.getId() == mc.player.getId()) {
                    packetEvent.cancel();
                    if(mode.isMode("OldGrim")) {
                        grimTicks = 6;
                    }
                }
            }
            if (packetEvent.getPacket() instanceof PlayPingS2CPacket && grimTicks > 0 && mode.isMode("OldGrim")) {
                packetEvent.cancel();
                grimTicks--;
            }
            if(packetEvent.getPacket() instanceof ExplosionS2CPacket){
                packetEvent.cancel();
            }
        }
    }
}
