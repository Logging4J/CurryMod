package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.visual.NoRender;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {

    @Inject(
            method = "renderInGameBackground",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderInGameBackgroundHEAD(DrawContext drawContext, CallbackInfo ci) {
        NoRender noRender = CurryMod.INSTANCE.moduleManager.getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.getGuiBackground().getValue()) {
            ci.cancel();
        }
    }
}
