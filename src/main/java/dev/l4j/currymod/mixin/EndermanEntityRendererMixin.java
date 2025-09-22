package dev.l4j.currymod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.fun.BasketBallPeople;
import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.client.render.entity.feature.EndermanEyesFeatureRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndermanEntityRenderer.class)
public class EndermanEntityRendererMixin {

    @ModifyReturnValue(
            method = "getTexture(Lnet/minecraft/client/render/entity/state/EndermanEntityRenderState;)Lnet/minecraft/util/Identifier;",
            at = @At("TAIL")
    )
    public Identifier getTexture(Identifier original) {
        return CurryMod.INSTANCE.moduleManager.getModule(BasketBallPeople.class).isEnabled() ? ResourceBank.BASKETBALL_PERSON : original;
    }

}
