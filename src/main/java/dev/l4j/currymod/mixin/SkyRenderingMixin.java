package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.fun.TudouSky;
import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SkyRendering.class)
public class SkyRenderingMixin {

    @Redirect(
            method = "renderSun",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/RenderLayer;getCelestial(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"
            )
    )
    public RenderLayer renderSunINVOKE$getCelestial(Identifier texture){
        if (CurryMod.INSTANCE.moduleManager.getModule(TudouSky.class).isEnabled()) {
            return RenderLayer.getCelestial(ResourceBank.TUDOU_SUN);
        } else {
            return RenderLayer.getCelestial(texture);
        }
    }

    @Redirect(
            method = "renderMoon",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/RenderLayer;getCelestial(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"
            )
    )
    public RenderLayer renderMoonINVOKE$getCelestial(Identifier texture) {
        if (CurryMod.INSTANCE.moduleManager.getModule(TudouSky.class).isEnabled()) {
            return RenderLayer.getCelestial(ResourceBank.TUDOU_MOON);
        } else {
            return RenderLayer.getCelestial(texture);
        }
    }
}
