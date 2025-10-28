package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.client.Capes;
import dev.l4j.currymod.client.module.modules.fun.Jew;
import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin {
    @Inject(
            method = "getSkinTextures",
            at = @At("RETURN"),
            cancellable = true
    )
    private void getSkinTexturesRETURN(CallbackInfoReturnable<SkinTextures> cir) {
        SkinTextures textures = cir.getReturnValue();

        Capes capes = CurryMod.INSTANCE.moduleManager.getModule(Capes.class);
        Jew jew = CurryMod.INSTANCE.moduleManager.getModule(Jew.class);

        cir.setReturnValue(new SkinTextures(
                jew.isEnabled() ? ResourceBank.JEW_SKIN : textures.texture(),
                textures.textureUrl(),
                capes.isEnabled() ? capes.getCapeMap().get(capes.getMode().getValue()) : textures.capeTexture(),
                textures.elytraTexture(),
                textures.model(),
                textures.secure()
        ));
    }
}
