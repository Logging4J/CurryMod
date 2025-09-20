package dev.l4j.currymod.mixin;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.listener.IRender2DListener;
import dev.l4j.currymod.util.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.profiler.Profilers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    private void renderTAIL(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        context.createNewRootLayer();

        Profilers.get().push(CurryMod.MOD_ID + "_render_2d");

        RenderUtils.unscaledProjection();

        IRender2DListener.Render2DEvent event = new IRender2DListener.Render2DEvent(context, context.getScaledWindowWidth(), context.getScaledWindowWidth(), tickCounter.getTickProgress(true));
        DietrichEvents2.global().call(IRender2DListener.Render2DEvent.ID, event);

        context.createNewRootLayer();

        RenderUtils.scaledProjection();

        Profilers.get().pop();
    }

}
