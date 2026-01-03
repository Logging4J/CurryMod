package dev.logging4j.currymod.mixin;

import net.minecraft.client.gui.screen.SplashOverlay;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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

}
