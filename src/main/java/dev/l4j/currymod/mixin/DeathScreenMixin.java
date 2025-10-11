package dev.l4j.currymod.mixin;

import dev.l4j.currymod.util.RenderUtils;
import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {

    protected DeathScreenMixin(Text title) {
        super(title);
    }

//    @Redirect(
//            method = "render",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"
//            )
//    )
//    public void render(DrawContext instance, TextRenderer textRenderer, Text text, int centerX, int y, int color) {}

    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
        context.drawTexture(RenderPipelines.GUI_TEXTURED, ResourceBank.RETARDATION, 0, 0, 0, 0, width, height, 256, 256);
    }
}
