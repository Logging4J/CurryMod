package dev.l4j.currymod.mixin;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.listener.IGameTickListener;
import dev.l4j.currymod.util.RenderUtils;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.util.Icons;
import net.minecraft.client.util.Window;
import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Unique private long lastTime;

    @Unique private boolean firstFrame;

    @Shadow @Final private Window window;

    @Shadow @Final private DefaultResourcePack defaultResourcePack;

    @Inject(
            method = "close",
            at = @At("TAIL")
    )
    public void closeTAIL(CallbackInfo ci) {
        try {
            CurryMod.INSTANCE.onShutdownClient();
        } catch (Exception e) {
            CurryMod.LOGGER.error("Error During Shutdown:", e);
        }
    }

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/Window;setIcon(Lnet/minecraft/resource/ResourcePack;Lnet/minecraft/client/util/Icons;)V"
            )
    )
    private void initINVOKE$setIcon(Window instance, ResourcePack resourcePack, Icons icons){}

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void initRETURN(RunArgs args, CallbackInfo ci) {
        try {
            window.setIcon(defaultResourcePack, SharedConstants.getGameVersion().stable() ? Icons.RELEASE : Icons.SNAPSHOT);
        } catch (Exception e) {
            CurryMod.LOGGER.error("Couldn't set icon", e);
        }
    }

    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    private void renderHEAD(CallbackInfo info) {
        long time = System.currentTimeMillis();

        if (firstFrame) {
            lastTime = time;
            firstFrame = false;
        }

        RenderUtils.frameTime = (time - lastTime) / 1000.0;
        lastTime = time;
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    public void tickHEAD(CallbackInfo ci) {
        IGameTickListener.GameTickEvent event = new IGameTickListener.GameTickEvent();
        DietrichEvents2.global().call(IGameTickListener.GameTickEvent.ID, event);
    }
}
