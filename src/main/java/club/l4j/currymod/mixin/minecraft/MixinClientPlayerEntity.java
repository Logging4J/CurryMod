package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.MovementEvent;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity extends AbstractClientPlayerEntity {

    @Shadow protected abstract void autoJump(float dx, float dz);

    public MixinClientPlayerEntity(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    public void move(MovementType movementType, Vec3d movement, CallbackInfo ci) {
        MovementEvent event = new MovementEvent(movementType, movement);
        CurryMod.EVENT_BUS.call(event);
        if(event.isCancelled()){
            ci.cancel();
        } else if (!movementType.equals(event.getType()) || !movement.equals(event.getVec())) {
            double d = getX();
            double e = getZ();
            move(event.getType(), event.getVec());
            autoJump((float) (getX() - d), (float) (getZ() - e));
        }
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean redirectUsingItem(ClientPlayerEntity player) {
        if (CurryMod.hackManager.getHack("NoSlow").isEnabled()) {
            return false;
        }
        return player.isUsingItem();
    }


}
