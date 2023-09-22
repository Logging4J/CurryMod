package wtf.l4j.mixin.mixins;

import net.minecraft.client.render.LightmapTextureManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.FullBright;

@Mixin(LightmapTextureManager.class)
public class MixinLightmapTextureManager {

    @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"))
    private void update(Args args) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(FullBright.class).orElseThrow().isEnabled()) {
            args.set(2, 0xFFFFFFFF);
        }
    }

}
