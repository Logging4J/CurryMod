package wtf.l4j.mixin.mixins;

import net.minecraft.client.util.Icons;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import wtf.l4j.api.utils.render.CurryIdentifier;
import wtf.l4j.api.utils.MinecraftInterface;

import java.io.InputStream;

@Mixin(Icons.class)
public class MixinIcons implements MinecraftInterface {


    @Inject(method = "getIcon", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void GetIcon(ResourcePack resourcePack, String string, CallbackInfoReturnable<InputSupplier<InputStream>> cir, String[] strings, InputSupplier<InputStream> inputSupplier) {
        if(strings.length == 2) {
            cir.setReturnValue(() -> mc.getResourceManager().getResourceOrThrow(new CurryIdentifier("icons/"+string)).getInputStream());
        }
    }
}
