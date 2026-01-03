package dev.logging4j.currymod.mixin;

import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.modules.visual.NoRender;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastManager.class)
public class ToastManagerMixin {
    @Inject(
            method = "draw",
            at = @At("HEAD"),
            cancellable = true
    )
    private void drawHEAD(DrawContext context, CallbackInfo ci) {
        NoRender noRender = CurryMod.getModuleManager().getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.getAdvancements().getValue()) {
            ci.cancel();
        }
    }
}
