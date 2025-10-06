package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.visual.NoRender;
import dev.l4j.currymod.client.module.modules.visual.ViewClip;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class CameraMixin {

    @Inject(
            method = "clipToSpace",
            at = @At("HEAD"),
            cancellable = true
    )

    public void clipToSpaceHEAD(float f, CallbackInfoReturnable<Float> cir) {
        ViewClip viewClip = CurryMod.INSTANCE.moduleManager.getModule(ViewClip.class);
        if (viewClip.isEnabled()) {
            cir.setReturnValue(((float) viewClip.getDistance().getValue()));
        }
    }

    @Inject(
            method = "getSubmersionType",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getSubmergedFluidStateHEAD(CallbackInfoReturnable<CameraSubmersionType> ci) {
        NoRender noRender = CurryMod.INSTANCE.moduleManager.getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.getLiquid().getValue()) {
            ci.setReturnValue(CameraSubmersionType.NONE);
        }
    }
}
