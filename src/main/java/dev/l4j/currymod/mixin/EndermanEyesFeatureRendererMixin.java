package dev.l4j.currymod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.fun.BasketBallPeople;
import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EndermanEyesFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndermanEyesFeatureRenderer.class)
public class EndermanEyesFeatureRendererMixin {

    @ModifyReturnValue(
            method = "getEyesTexture",
            at = @At("TAIL")
    )
    public RenderLayer getEyesTexture(RenderLayer original) {
        return CurryMod.INSTANCE.moduleManager.getModule(BasketBallPeople.class).isEnabled() ? RenderLayer.getEyes(ResourceBank.BASKETBALL_PERSON_EYES) : original;
    }

}
