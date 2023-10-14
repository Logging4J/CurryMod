package wtf.l4j.mixin.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import de.florianmichael.dietrichevents2.DietrichEvents2;
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
import wtf.l4j.CurryMod;
import wtf.l4j.api.event.ChatListener;
import wtf.l4j.api.event.DismountListener;
import wtf.l4j.api.event.Type;
import wtf.l4j.impl.modules.combat.Velocity;
import wtf.l4j.impl.modules.movement.NoSlow;
import wtf.l4j.impl.modules.visual.FreeCam;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity extends AbstractClientPlayerEntity {

    public MixinClientPlayerEntity(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @ModifyExpressionValue(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean redirectUsingItem(boolean isUsingItem) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(NoSlow.class).get().isEnabled()){
            return false;
        }
        return isUsingItem;
    }

    @Inject(method = "pushOutOfBlocks", at = @At("HEAD"), cancellable = true)
    private void pushOutOfBlocks(double x, double z, CallbackInfo ci) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(Velocity.class).get().isEnabled() && Velocity.push.isEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method = "dismountVehicle", at = @At("TAIL"))
    public void dismountVehicle(CallbackInfo ci) {
        DismountListener.DismountEvent dismountEvent = new DismountListener.DismountEvent();
        //@formatter:off
        DietrichEvents2.global().postInternal(DismountListener.DismountEvent.ID, dismountEvent);
        //@formatter:on
    }

}