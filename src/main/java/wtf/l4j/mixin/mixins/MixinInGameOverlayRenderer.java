package wtf.l4j.mixin.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.NoRender;

@Mixin(InGameOverlayRenderer.class)
public class MixinInGameOverlayRenderer {

    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderFireOverlay(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(NoRender.class).orElseThrow().isEnabled() && NoRender.fire.isEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method = "renderUnderwaterOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderUnderwaterOverlay(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(NoRender.class).orElseThrow().isEnabled() && NoRender.liquid.isEnabled()){
            ci.cancel();
        }
    }
}
