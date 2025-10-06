package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.visual.NoRender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {

    @Inject(
            method = "renderUnderwaterOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void renderUnderwaterOverlayHEAD(MinecraftClient client, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        NoRender noRender = CurryMod.INSTANCE.moduleManager.getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.getLiquid().getValue()){
            ci.cancel();
        }
    }

    @Inject(
            method = "renderFireOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void renderFireOverlayHEAD(MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        NoRender noRender = CurryMod.INSTANCE.moduleManager.getModule(NoRender.class);

        if(noRender.isEnabled() && noRender.getFire().getValue()) {
            ci.cancel();
        }
    }

    @Inject(
            method = "renderInWallOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void renderInWallOverlayHEAD(Sprite sprite, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        NoRender noRender = CurryMod.INSTANCE.moduleManager.getModule(NoRender.class);

        if(noRender.isEnabled() && noRender.getWall().getValue()) {
            ci.cancel();
        }
    }
}
