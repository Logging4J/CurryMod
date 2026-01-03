package dev.logging4j.currymod.mixin;

import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.modules.visual.NoRender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {

    @Inject(
            method = "renderFireOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void renderFireOverlayHEAD(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        NoRender noRender = CurryMod.getModuleManager().getModule(NoRender.class);

        if(noRender.isEnabled() && noRender.getFire().getValue()) {
            ci.cancel();
        }
    }

    @Inject(
            method = "renderUnderwaterOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void renderUnderwaterOverlayHEAD(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        NoRender noRender = CurryMod.getModuleManager().getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.getLiquid().getValue()){
            ci.cancel();
        }
    }

    @Inject(
            method = "renderInWallOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void renderInWallOverlayHEAD(Sprite sprite, MatrixStack matrices, CallbackInfo ci) {
        NoRender noRender = CurryMod.getModuleManager().getModule(NoRender.class);

        if(noRender.isEnabled() && noRender.getWall().getValue()) {
            ci.cancel();
        }
    }
}
