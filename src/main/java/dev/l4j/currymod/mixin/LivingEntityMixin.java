package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.visual.SwingSpeed;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(
            method = "getHandSwingDuration",
            at = @At("HEAD"),
            cancellable = true
    )
    public void getHandSwingDurationInjectHEAD(CallbackInfoReturnable<Integer> cir) {
        SwingSpeed swingSpeed = CurryMod.INSTANCE.moduleManager.getModule(SwingSpeed.class);
        if (swingSpeed.isEnabled()) {
            cir.setReturnValue(swingSpeed.getSpeed().getValue());
        }
    }
}
