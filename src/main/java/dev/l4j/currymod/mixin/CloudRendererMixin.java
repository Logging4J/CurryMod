package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.fun.NaziClouds;
import dev.l4j.currymod.util.ResourceBank;
import net.minecraft.client.render.CloudRenderer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.IOException;
import java.io.InputStream;

@Mixin(CloudRenderer.class)
public class CloudRendererMixin {

    @Redirect(
            method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Ljava/util/Optional;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/resource/ResourceManager;open(Lnet/minecraft/util/Identifier;)Ljava/io/InputStream;"
            )
    )
    private InputStream prepareINVOKE$open(ResourceManager resourceManager, Identifier original) throws IOException {
        if (CurryMod.INSTANCE.moduleManager.getModule(NaziClouds.class).isEnabled()) {
            return resourceManager.open(ResourceBank.NAZI_CLOUDS);
        }
        return resourceManager.open(original);
    }
}
