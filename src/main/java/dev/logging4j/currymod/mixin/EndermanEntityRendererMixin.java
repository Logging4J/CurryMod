package dev.logging4j.currymod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.modules.fun.BasketBallPeople;
import dev.logging4j.currymod.util.ResourceBank;
import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndermanEntityRenderer.class)
public class EndermanEntityRendererMixin {

    @ModifyReturnValue(
            method = "getTexture(Lnet/minecraft/entity/mob/EndermanEntity;)Lnet/minecraft/util/Identifier;",
            at = @At("TAIL")
    )
    public Identifier getTexture(Identifier original) {
        return CurryMod.getModuleManager().getModule(BasketBallPeople.class).isEnabled() ? ResourceBank.BASKETBALL_PERSON : original;
    }

}
