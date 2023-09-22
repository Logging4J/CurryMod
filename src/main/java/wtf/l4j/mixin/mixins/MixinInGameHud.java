package wtf.l4j.mixin.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import wtf.l4j.CurryMod;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.NoRender;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, float tickDelta, CallbackInfo info) {
        if(client.player != null || client.world != null) {
            for (HudElement hudElement : CurryMod.getInstance().getManagers().getHudManager().getHudElements()) {
                if (hudElement.isEnabled()) {
                    hudElement.onRender(context, tickDelta);
                }
            }
        }
    }

    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V", ordinal = 0))
    private void onRenderPumpkinOverlay(Args args) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(NoRender.class).get().isEnabled() && NoRender.pumpkin.isEnabled()){
            args.set(2, 0f);
        }
    }

    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V", ordinal = 1))
    private void onRenderPowderedSnowOverlay(Args args) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(NoRender.class).get().isEnabled() && NoRender.snow.isEnabled()) {
            args.set(2, 0f);
        }
    }

    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderPortalOverlay(DrawContext context, float f, CallbackInfo info) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(NoRender.class).orElseThrow().isEnabled() && NoRender.portal.isEnabled()) {
            info.cancel();
        }
    }

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"), cancellable = true)
    protected void renderStatusEffectOverlay(DrawContext context, CallbackInfo ci) {
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(NoRender.class).orElseThrow().isEnabled() && NoRender.potion.isEnabled()){
            ci.cancel();
        }
    }

}
