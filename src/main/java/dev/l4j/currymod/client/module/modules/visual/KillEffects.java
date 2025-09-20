package dev.l4j.currymod.client.module.modules.visual;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.listener.IPacketReceiveListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;

@Module.Info(name = "KillEffects", description = "Kill effects", category = Module.Category.VISUAL)
public class KillEffects extends Module implements IPacketReceiveListener {

    private final OptionBoolean self = new OptionBoolean("Self", true);

    public KillEffects() {
        addOptions(self);
    }

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(PacketReceiveEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(PacketReceiveEvent.ID, this);
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof DeathMessageS2CPacket packet) {
            Entity entity = mc.world.getEntityById(packet.playerId());

            if (entity == mc.player && !self.getValue())  return;

            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, mc.world);
            lightningEntity.updatePositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());
            mc.world.addEntity(lightningEntity);
        }
    }
}
