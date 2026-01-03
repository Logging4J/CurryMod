package dev.logging4j.currymod.mixin;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.listener.IRender2DListener;
import dev.logging4j.currymod.module.modules.misc.ChatModifier;
import dev.logging4j.currymod.module.modules.visual.NoRender;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    private void renderTAIL(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        IRender2DListener.Render2DEvent event = new IRender2DListener.Render2DEvent(context, tickCounter);
        DietrichEvents2.global().call(IRender2DListener.Render2DEvent.ID, event);
    }

    @ModifyArgs(
            method = "renderMiscOverlays",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V",
                    ordinal = 0
            )
    )
    private void renderMiscOverlaysINVOKE$renderOverlay0(Args args) {
        NoRender noRender = CurryMod.getModuleManager().getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.getPumpkin().getValue()) {
            args.set(2, 0f);
        }
    }

    @ModifyArgs(
            method = "renderMiscOverlays",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V",
                    ordinal = 1
            )
    )
    private void renderMiscOverlaysINVOKE$renderOverlay1(Args args) {
        NoRender noRender = CurryMod.getModuleManager().getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.getPowderSnow().getValue()) {
            args.set(2, 0f);
        }
    }

    @Inject(
            method = "renderSpyglassOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderSpyglassOverlayHEAD(DrawContext context, float scale, CallbackInfo ci) {
        NoRender noRender = CurryMod.getModuleManager().getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.getSpyGlass().getValue()) {
            ci.cancel();
        }
    }

    @Inject(
            method = "renderPortalOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderPortalOverlayHEAD(DrawContext context, float nauseaStrength, CallbackInfo ci) {
        NoRender noRender = CurryMod.getModuleManager().getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.getPortal().getValue()) {
            ci.cancel();
        }
    }

    @Inject(
            method = "clear",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/ChatHud;clear(Z)V"),
            cancellable = true
    )
    private void clearINVOKE$clear(CallbackInfo ci) {
        ChatModifier chatModifier = CurryMod.getModuleManager().getModule(ChatModifier.class);

        if (chatModifier.isEnabled() && chatModifier.getKeepHistory().getValue()) {
            ci.cancel();
        }
    }
}
