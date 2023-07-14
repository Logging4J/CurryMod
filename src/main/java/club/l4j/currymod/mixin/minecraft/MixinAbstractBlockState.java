package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class MixinAbstractBlockState {

    @Inject(method = "getAmbientOcclusionLightLevel", at = @At("TAIL"), cancellable = true)
    public void getAmbientOcclusionLightLevel(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if(CurryMod.featureManager.getHack("Xray").isEnabled()){
            cir.setReturnValue(1.0f);
        }
    }


}
