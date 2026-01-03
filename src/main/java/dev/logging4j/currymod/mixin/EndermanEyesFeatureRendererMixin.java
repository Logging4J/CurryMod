package dev.logging4j.currymod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.modules.fun.BasketBallPeople;
import dev.logging4j.currymod.util.ResourceBank;
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
        return CurryMod.getModuleManager().getModule(BasketBallPeople.class).isEnabled() ? RenderLayer.getEyes(ResourceBank.BASKETBALL_PERSON_EYES) : original;
    }

}
