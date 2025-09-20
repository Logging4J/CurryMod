package dev.l4j.currymod.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.function.IntSupplier;

@Mixin(SplashOverlay.class)
public class SplashOverlayMixin {

    @Mutable @Shadow @Final private static IntSupplier BRAND_ARGB;

    @Inject(
            method = "<clinit>",
            at = @At("RETURN")
    )
    private static void clinitReturn(CallbackInfo ci) {
        BRAND_ARGB = Color.BLACK::getRGB;
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIFFIIIIIII)V",
                    ordinal = 0
            )
    )
    public void renderINVOKE$drawTexture(DrawContext instance, RenderPipeline pipeline, Identifier sprite, int x, int y, float u, float v, int width, int height, int regionWidth, int regionHeight, int textureWidth, int textureHeight, int color) {
        instance.drawTexture(pipeline, ResourceBank.LOGO, instance.getScaledWindowWidth() / 2 - 100, 10, 0, 0, 200, 200, 200, 200, color);
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIFFIIIIIII)V",
                    ordinal = 1
            )
    )
    public void renderINVOKE$drawTexture1(DrawContext instance, RenderPipeline pipeline, Identifier sprite, int x, int y, float u, float v, int width, int height, int regionWidth, int regionHeight, int textureWidth, int textureHeight, int color) {}
}
