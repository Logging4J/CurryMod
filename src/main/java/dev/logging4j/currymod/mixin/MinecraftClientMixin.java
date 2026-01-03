package dev.logging4j.currymod.mixin;

import dev.logging4j.currymod.CurryMod;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow @Final private Window window;

    @Shadow @Final private DefaultResourcePack defaultResourcePack;

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
            window.setIcon(defaultResourcePack, SharedConstants.getGameVersion().isStable() ? Icons.RELEASE : Icons.SNAPSHOT);
        } catch (Exception e) {
            CurryMod.LOGGER.error("Couldn't set icon", e);
        }
    }
}
