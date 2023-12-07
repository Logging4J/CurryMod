package wtf.l4j.mixin.mixins;

import net.minecraft.block.AbstractBlock;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.Xray;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class MixinAbstractBlockState {

    @Inject(method = "getAmbientOcclusionLightLevel", at = @At("TAIL"), cancellable = true)
    public void getAmbientOcclusionLightLevel(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(Xray.class).isEnabled()){
            cir.setReturnValue(1.0f);
        }
    }
}
