package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import net.minecraft.client.render.chunk.ChunkOcclusionDataBuilder;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkOcclusionDataBuilder.class)
public class MixinChunkOcclusionDataBuilder {

    @Inject(method = "markClosed", at = @At("HEAD"), cancellable = true)
    public void markClosed(BlockPos pos, CallbackInfo ci) {
        if(CurryMod.featureManager.getHack("Xray").isEnabled()){
            ci.cancel();
        }
    }
}
