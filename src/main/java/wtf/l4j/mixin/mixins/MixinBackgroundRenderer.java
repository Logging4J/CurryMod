package wtf.l4j.mixin.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.NoRender;

@Mixin(BackgroundRenderer.class)
public class MixinBackgroundRenderer {

    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        if (Managers.getModuleManager().getModule(NoRender.class).orElseThrow().isEnabled() && NoRender.fog.isEnabled()) {
            if (fogType == BackgroundRenderer.FogType.FOG_TERRAIN) {
                RenderSystem.setShaderFogStart(viewDistance * 4);
                RenderSystem.setShaderFogEnd(viewDistance * 4.25f);
            }
        }
    }
}
