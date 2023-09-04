package wtf.l4j.mixin.mixins;

import net.fabricmc.fabric.impl.client.indigo.renderer.render.TerrainRenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.world.BlockUtils;
import wtf.l4j.impl.modules.visual.Xray;

@Mixin(TerrainRenderContext.class)
public class MixinTerrainRenderContext {

    @Inject(method = "tessellateBlock", at = @At("HEAD"), cancellable = true)
    public void tessellateBlock(BlockState blockState, BlockPos blockPos, BakedModel model, MatrixStack matrixStack, CallbackInfo ci) {
        if(Managers.getModuleManager().getModule(Xray.class).orElseThrow().isEnabled()){
            if(!BlockUtils.ORE_BLOCKS.contains(blockState.getBlock())){
                ci.cancel();
            }
        }
    }
}
