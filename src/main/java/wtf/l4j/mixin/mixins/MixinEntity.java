package wtf.l4j.mixin.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.combat.Velocity;
import wtf.l4j.impl.modules.visual.FreeLook;

@Mixin(Entity.class)
public class MixinEntity {

    @Inject(method = "changeLookDirection", at = @At("HEAD"), cancellable = true)
    public void changeLookDirection(double cursorDeltaX, double cursorDeltaY, CallbackInfo ci) {
        if(Managers.getModuleManager().getModule(FreeLook.class).orElseThrow().isEnabled()) {
            double pitchDelta = (cursorDeltaY * 0.15);
            double yawDelta = (cursorDeltaX * 0.15);
            FreeLook.cameraPitch = MathHelper.clamp(FreeLook.cameraPitch + (float) pitchDelta, -90.0f, 90.0f);
            FreeLook.cameraYaw += (float) yawDelta;
            ci.cancel();
        }
    }

    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    public void pushAwayFrom(Entity entity, CallbackInfo ci) {
        if(Managers.getModuleManager().getModule(Velocity.class).orElseThrow().isEnabled() && Velocity.push.isEnabled()){
            ci.cancel();
        }
    }
}
