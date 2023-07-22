package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({LightmapTextureManager.class})
public class MixinLightMapTextureManager {

    @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"))
    private void update(Args args) {
        if (CurryMod.hackManager.getHack("FullBright").isEnabled()) {
            args.set(2, 0xFFFFFFFF);
        }
    }

}
