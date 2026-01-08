package dev.logging4j.currymod.module.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.listener.IPacketReceiveListener;
import dev.logging4j.currymod.mixin.accessor.EntityVelocityUpdateS2CPacketAccessor;
import dev.logging4j.currymod.mixin.accessor.ExplosionS2CPacketAccessor;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.options.OptionBoolean;
import dev.logging4j.currymod.module.option.options.OptionNumber;
import lombok.Getter;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

@Module.Info(name = "Velocity", description = "change knockback", category = Module.Category.MOVEMENT)
public class Velocity extends Module implements IPacketReceiveListener {

    private final OptionNumber<Double> horizontal = new OptionNumber<>("Horizontal", 0.0D, 0.0D, 1.0D, 0.01D);
    private final OptionNumber<Double> vertical = new OptionNumber<>("Vertical", 0.0D, 0.0D, 1.0D, 0.01D);
    @Getter private final OptionBoolean noPushEntities = new OptionBoolean("NoPushEntities", true);
    @Getter private final OptionBoolean noPushBlocks = new OptionBoolean("NoPushBlocks", true);
    //TODO
    @Getter private final OptionBoolean noPushLiquids = new OptionBoolean("NoPushLiquids", true);


    public Velocity() {
        addOptions(horizontal, vertical, noPushEntities, noPushBlocks, noPushLiquids);
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
        if (event.getPacket() instanceof EntityVelocityUpdateS2CPacket packet) {
            if (packet.getEntityId() == mc.player.getId()) {
                ((EntityVelocityUpdateS2CPacketAccessor) packet).setVelocityX((int) (packet.getVelocityX() * 8000.0D * horizontal.getValue()));
                ((EntityVelocityUpdateS2CPacketAccessor) packet).setVelocityY((int) (packet.getVelocityY() * 8000.0D * vertical.getValue()));
                ((EntityVelocityUpdateS2CPacketAccessor) packet).setVelocityZ((int) (packet.getVelocityZ() * 8000.0D * horizontal.getValue()));
            }
        }

        if (event.getPacket() instanceof ExplosionS2CPacket packet) {
            ((ExplosionS2CPacketAccessor) packet).setPlayerVelocityX((float) (packet.getPlayerVelocityX() * horizontal.getValue()));
            ((ExplosionS2CPacketAccessor) packet).setPlayerVelocityY((float) (packet.getPlayerVelocityY() * vertical.getValue()));
            ((ExplosionS2CPacketAccessor) packet).setPlayerVelocityZ((float) (packet.getPlayerVelocityZ() * horizontal.getValue()));
        }
    }
}
