package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.visual.Brightness;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {

    @Redirect(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Double;floatValue()F",
                    ordinal = 1
            )
    )
    private float updateINVOKE$floatValue(Double instance) {
        if (CurryMod.INSTANCE.moduleManager.getModule(Brightness.class).isEnabled()) return 1250;
        return instance.floatValue();
    }

}
