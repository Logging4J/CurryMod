package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.impl.hackimpl.visual.NoRender;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void tiltViewWhenHurt(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (CurryMod.featureManager.getHack("NoRender").isEnabled() && NoRender.hurt.isEnabled()) {
            ci.cancel();
        }
    }

}
