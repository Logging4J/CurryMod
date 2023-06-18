package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.impl.hackimpl.combat.Velocity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidState.class)
public class MixinFluidSate {

    @Inject(method = "getVelocity", at = @At("HEAD"), cancellable = true)
    public void getVelocity(BlockView world, BlockPos pos, CallbackInfoReturnable<Vec3d> cir) {
        if(CurryMod.featureManager.getHack("Velocity").isEnabled() && Velocity.noWaterVel.isEnabled()){
            cir.setReturnValue(Vec3d.ZERO);
        }
    }
}
