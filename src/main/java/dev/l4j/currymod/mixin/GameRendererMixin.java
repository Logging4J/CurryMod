package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.visual.NoRender;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void tiltViewWhenHurtHEAD(MatrixStack matrices, float tickProgress, CallbackInfo ci) {
        NoRender noRender = CurryMod.INSTANCE.moduleManager.getModule(NoRender.class);
        if (noRender.isEnabled() && noRender.getHurtCam().getValue()) {
            ci.cancel();
        }
    }
}
