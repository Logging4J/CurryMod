package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.util.player.PlayerUtil;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.TerrainRenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TerrainRenderContext.class)
public class MixinTerrainRenderContext {

    @Inject(method = "tessellateBlock", at = @At("HEAD"), cancellable = true)
    public void tessellateBlock(BlockState blockState, BlockPos blockPos, BakedModel model, MatrixStack matrixStack, CallbackInfo ci) {
        if(CurryMod.hackManager.getHack("Xray").isEnabled()){
            if(!PlayerUtil.ORE_BLOCKS.contains(blockState.getBlock())){
                ci.cancel();
                return;
            }
        }
    }
}
