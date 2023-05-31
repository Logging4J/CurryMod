package club.l4j.currymod.mixin.minecraft;

import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityVelocityUpdateS2CPacket.class)
public interface IEntityVelocityUpdateS2CPacket {

    @Mutable
    @Accessor("velocityX")
    void setVelocityX(int x);

    @Mutable
    @Accessor("velocityY")
    void setVelocityY(int x);

    @Mutable
    @Accessor("velocityZ")
    void setVelocityZ(int x);

}
