package dev.l4j.currymod.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LogoDrawer.class)
public class LogoDrawerMixin {

    @ModifyArg(
            method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIFFIIIII)V",
                    ordinal = 0
            ), index = 1
    )
    public Identifier drawINVOKE$drawTexture0(Identifier sprite) {
        return ResourceBank.TITLE;
    }

    @Redirect(
            method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIFFIIIII)V",
                    ordinal = 1
            )
    )
    public void drawINVOKE$drawTexture1(DrawContext instance, RenderPipeline pipeline, Identifier sprite, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight, int color) {}
}
