package wtf.l4j.impl.modules.combat;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

import net.minecraft.network.packet.s2c.query.QueryPongS2CPacket;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.event.Type;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.utils.MovementUtils;

@ModuleInfo(name = "PlayerModifier", desc = "Modify player", category = Category.COMBAT)
public class PlayerModifier extends Module implements GameTickListener, PacketListener {

    //Hit Velocity for 2b WIP
    public OptionMode mode = new OptionMode("Mode", "Cancel", "Cancel","2b2t");
    public static OptionBoolean push = new OptionBoolean("NoPush", true);
    public static OptionBoolean fastStop = new OptionBoolean("FastStop", true);

    public PlayerModifier(){
        addOptions(push, mode, fastStop);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
        DietrichEvents2.global().subscribe(PacketListener.PacketEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        DietrichEvents2.global().unsubscribe(PacketListener.PacketEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if(!MovementUtils.isMoving() && fastStop.isEnabled()){
            mc.player.setVelocity(0, mc.player.getVelocity().y, 0);
        }
    }

    @Override
    public void onPacket(PacketEvent packetEvent) {
        if(packetEvent.getType() == Type.INCOMING){
            if (packetEvent.getPacket() instanceof ExplosionS2CPacket) {
                if(mode.isMode("Cancel") || mode.isMode("2b2t")) {
                    packetEvent.setCancelled(true);
                }
            } else if (packetEvent.getPacket() instanceof EntityVelocityUpdateS2CPacket) {
                if(mode.isMode("Cancel")){
                    packetEvent.setCancelled(true);
                }
            }
        }
    }
}
