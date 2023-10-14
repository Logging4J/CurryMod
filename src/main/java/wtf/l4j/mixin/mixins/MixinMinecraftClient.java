package wtf.l4j.mixin.mixins;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.Icons;
import net.minecraft.client.util.Window;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.ResourcePack;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.utils.ClientInfoInterface;
import wtf.l4j.impl.modules.client.Colors;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient implements ClientInfoInterface {
    @Shadow @Final private Window window;

    @Shadow @Final private DefaultResourcePack defaultResourcePack;

    @Shadow @Nullable public ClientPlayerEntity player;

    @Shadow @Nullable public ClientWorld world;

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if(player != null && world != null) {
            //@formatter:off
            DietrichEvents2.global().postInternal(GameTickListener.GameTickEvent.ID, new GameTickListener.GameTickEvent());
            //@formatter:on
        }
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(Colors.class).orElseThrow().isEnabled()) {
            CurryMod.getInstance().getManagers().getColorManager().setRed(Colors.red.getIntValue());
            CurryMod.getInstance().getManagers().getColorManager().setGreen(Colors.green.getIntValue());
            CurryMod.getInstance().getManagers().getColorManager().setBlue(Colors.blue.getIntValue());
        }
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setIcon(Lnet/minecraft/resource/ResourcePack;Lnet/minecraft/client/util/Icons;)V"))
    private void redirect_init(Window instance, ResourcePack resourcePack, Icons icons) {}

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init1(RunArgs args, CallbackInfo ci) {
        CurryMod.getInstance().startup();
        try {
            window.setIcon(defaultResourcePack, SharedConstants.getGameVersion().isStable() ? Icons.RELEASE : Icons.SNAPSHOT);
        } catch (Exception e) {
            CurryMod.getInstance().getLogger().error("Couldn't set icon", e);
        }
    }

    @ModifyArg(method = "updateWindowTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setTitle(Ljava/lang/String;)V"))
    public String modifyUpdateWindowTitle(String title){
        return "PigHack.CC v" + version + " | ETHEREUM MINER EDITION";
    }

    @Inject(method = "close", at= @At("HEAD"))
    public void close(CallbackInfo ci) {
        CurryMod.getInstance().shutdown();
    }

}
