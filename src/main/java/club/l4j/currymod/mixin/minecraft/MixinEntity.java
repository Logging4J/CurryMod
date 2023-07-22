package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.impl.hacks.combat.Velocity;
import club.l4j.currymod.impl.hacks.visual.FreeLook;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class MixinEntity {

    @Inject(method = "changeLookDirection", at = @At("HEAD"), cancellable = true)
    public void changeLookDirection(double cursorDeltaX, double cursorDeltaY, CallbackInfo ci) {
        if(CurryMod.hackManager.getHack("FreeLook").isEnabled()) {
            double pitchDelta = (cursorDeltaY * 0.15);
            double yawDelta = (cursorDeltaX * 0.15);
            FreeLook.cameraPitch = MathHelper.clamp(FreeLook.cameraPitch + (float) pitchDelta, -90.0f, 90.0f);
            FreeLook.cameraYaw += (float) yawDelta;
            ci.cancel();
        }
    }

    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    public void pushAwayFrom(Entity entity, CallbackInfo ci) {
        if(CurryMod.hackManager.getHack("Velocity").isEnabled() && Velocity.noPush.isEnabled()){
            ci.cancel();
        }
    }

}
