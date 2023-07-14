package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.util.player.PlayerUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockModelRenderer.class)
public abstract class MixinBlockModelRenderer {

    @Inject(method = "renderSmooth", at = @At("HEAD"), cancellable = true)
    public void renderSmooth(BlockRenderView world, BakedModel model, BlockState state, BlockPos pos, MatrixStack matrices, VertexConsumer vertexConsumer, boolean cull, Random random, long seed, int overlay, CallbackInfo ci) {
        if(CurryMod.featureManager.getHack("Xray").isEnabled()){
            if(PlayerUtil.ORE_BLOCKS.contains(state.getBlock())){
                ci.cancel();
                return;
            }
        }
    }
}
