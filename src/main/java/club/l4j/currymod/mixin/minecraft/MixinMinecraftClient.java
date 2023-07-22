package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.TickEvent;
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
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Shadow @Final private Window window;

    @Shadow @Final private DefaultResourcePack defaultResourcePack;

    @ModifyArg(method = "updateWindowTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setTitle(Ljava/lang/String;)V"))
    public String updateWindowTitle(String title){
        return CurryMod.MOD_NAME + " b" + CurryMod.VERSION;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setIcon(Lnet/minecraft/resource/ResourcePack;Lnet/minecraft/client/util/Icons;)V"))
    private void redirect_init(Window instance, ResourcePack resourcePack, Icons icons) {}

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init1(RunArgs args, CallbackInfo ci) {
        try {
            window.setIcon(defaultResourcePack, SharedConstants.getGameVersion().isStable() ? Icons.RELEASE : Icons.SNAPSHOT);
        } catch (IOException e) {
            CurryMod.LOGGER.error("Couldn't set icon", e);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void onTick(CallbackInfo info){
        CurryMod.EVENT_BUS.call(new TickEvent());
    }

    @Inject(method = "close", at = @At("HEAD"), cancellable = true)
    public void close(CallbackInfo info){
        CurryMod.getInstance.onClose();
    }
}
