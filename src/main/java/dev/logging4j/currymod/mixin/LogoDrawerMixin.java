package dev.logging4j.currymod.mixin;

import dev.logging4j.currymod.util.ResourceBank;
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
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V",
                    ordinal = 0
            ), index = 0
    )
    public Identifier drawINVOKE$drawTexture0(Identifier texture) {
        return ResourceBank.TITLE;
    }

    @Redirect(
            method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V",
                    ordinal = 1
            )
    )
    public void drawINVOKE$drawTexture1(DrawContext instance, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {}
}
