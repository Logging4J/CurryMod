package dev.l4j.currymod.mixin;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.listener.IPostPlayerTickListener;
import dev.l4j.currymod.listener.IPrePlayerTickListener;
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

}
