package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.Render2DEvent;
import club.l4j.currymod.feature.impl.hackimpl.visual.NoRender;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, float tickDelta, CallbackInfo info) {
        CurryMod.EVENT_BUS.call(new Render2DEvent(context, tickDelta));
    }

    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V", ordinal = 0))
    private void onRenderPumpkinOverlay(Args args) {
        if (CurryMod.featureManager.getHack("NoRender").isEnabled() && NoRender.pumpkin.isEnabled()){
            args.set(2, 0f);
        }
    }

    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V", ordinal = 1))
    private void onRenderPowderedSnowOverlay(Args args) {
        if (CurryMod.featureManager.getHack("NoRender").isEnabled() && NoRender.snow.isEnabled()) {
            args.set(2, 0f);
        }
    }

    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderPortalOverlay(DrawContext context, float f, CallbackInfo info) {
        if (CurryMod.featureManager.getHack("NoRender").isEnabled() && NoRender.portal.isEnabled()) {
            info.cancel();
        }
    }

}
