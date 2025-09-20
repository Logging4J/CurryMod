package dev.l4j.currymod.mixin;

import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.BufferedReader;
import java.io.IOException;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {

    @Redirect(
            method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Ljava/util/List;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/resource/ResourceManager;openAsReader(Lnet/minecraft/util/Identifier;)Ljava/io/BufferedReader;"
            )
    )
    public BufferedReader prepareINVOKE$openAsReader(ResourceManager instance, Identifier identifier) throws IOException {
        return MinecraftClient.getInstance().getResourceManager().openAsReader(ResourceBank.SPLASHES);
    }
}
