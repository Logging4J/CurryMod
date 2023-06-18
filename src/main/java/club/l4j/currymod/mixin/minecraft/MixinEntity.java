package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.impl.hackimpl.combat.Velocity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class MixinEntity {

    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    public void pushAwayFrom(Entity entity, CallbackInfo ci) {
        if(CurryMod.featureManager.getHack("Velocity").isEnabled() && Velocity.noPush.isEnabled()){
            ci.cancel();
        }
    }

}
