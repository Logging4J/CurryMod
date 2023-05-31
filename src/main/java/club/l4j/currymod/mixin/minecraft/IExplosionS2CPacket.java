package club.l4j.currymod.mixin.minecraft;

import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ExplosionS2CPacket.class)
public interface IExplosionS2CPacket {

    @Mutable
    @Accessor("playerVelocityX")
    void setVelocityX(float x);

    @Mutable
    @Accessor("playerVelocityY")
    void setVelocityY(float y);

    @Mutable
    @Accessor("playerVelocityZ")
    void setVelocityZ(float z);
}
