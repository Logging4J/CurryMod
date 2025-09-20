package dev.l4j.currymod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.l4j.currymod.util.MinecraftInterface;
import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.util.Icons;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;

@Mixin(Icons.class)
public class IconsMixin implements MinecraftInterface {

    @Inject(method = "getIcon", at = @At("RETURN"), cancellable = true)
    private void getIconReturn(ResourcePack resourcePack, String string, CallbackInfoReturnable<InputSupplier<InputStream>> cir, @Local String[] strings) {
        if(strings.length == 2) {
            cir.setReturnValue(() -> mc.getResourceManager().getResourceOrThrow(ResourceBank.of("icons/" + string)).getInputStream());
        }
    }
}
