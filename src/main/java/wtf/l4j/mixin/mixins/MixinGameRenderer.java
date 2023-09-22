package wtf.l4j.mixin.mixins;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.NoRender;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void tiltViewWhenHurt(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(NoRender.class).orElseThrow().isEnabled() && NoRender.hurt.isEnabled()) {
            ci.cancel();
        }
    }

}
