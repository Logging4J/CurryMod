package wtf.l4j.mixin.mixins;

import net.minecraft.client.render.chunk.ChunkOcclusionDataBuilder;
import net.minecraft.util.math.BlockPos;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.Xray;

@Mixin(ChunkOcclusionDataBuilder.class)
public class MixinChunkOcclusionDataBuilder {

    @Inject(method = "markClosed", at = @At("HEAD"), cancellable = true)
    public void markClosed(BlockPos pos, CallbackInfo ci) {
        if(Managers.getModuleManager().getModule(Xray.class).orElseThrow().isEnabled()){
            ci.cancel();
        }
    }
}
