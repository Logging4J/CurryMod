package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.cape.CapeManager;
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

        boolean customCape = CurryMod.INSTANCE.capeManager.customCape;
        CapeManager.Cape cape = CurryMod.INSTANCE.capeManager.currentCape;
        Jew jew = CurryMod.INSTANCE.moduleManager.getModule(Jew.class);

        cir.setReturnValue(new SkinTextures(
                jew.isEnabled() ? ResourceBank.JEW_SKIN : textures.texture(),
                textures.textureUrl(),
                customCape ? cape == null ? textures.capeTexture() : cape.identifier() : textures.capeTexture(),
                textures.elytraTexture(),
                textures.model(),
                textures.secure()
        ));
    }
}
