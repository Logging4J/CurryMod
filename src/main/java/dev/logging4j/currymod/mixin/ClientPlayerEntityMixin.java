package dev.logging4j.currymod.mixin;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.listener.IPostPlayerTickListener;
import dev.logging4j.currymod.listener.IPrePlayerTickListener;
import dev.logging4j.currymod.module.modules.movement.Velocity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V",
                    shift = At.Shift.AFTER
            )
    )
    public void tickINVOKE$tick(CallbackInfo ci) {
        IPrePlayerTickListener.PrePlayerTickEvent event = new IPrePlayerTickListener.PrePlayerTickEvent();
        DietrichEvents2.global().call(IPrePlayerTickListener.PrePlayerTickEvent.ID, event);
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;sendMovementPackets()V",
                    shift = At.Shift.AFTER
            )
    )
    public void tickINVOKE$sendMovementPackets(CallbackInfo ci) {
        IPostPlayerTickListener.PostPlayerTickEvent event = new IPostPlayerTickListener.PostPlayerTickEvent();
        DietrichEvents2.global().call(IPostPlayerTickListener.PostPlayerTickEvent.ID, event);
    }

    @Inject(method = "pushOutOfBlocks", at = @At("HEAD"), cancellable = true)
    private void onPushOutOfBlocks(double x, double z, CallbackInfo ci) {
        Velocity velocity = CurryMod.getModuleManager().getModule(Velocity.class);

        if (velocity.isEnabled() && velocity.getNoPushBlocks().getValue()) {
            ci.cancel();
        }
    }
}
