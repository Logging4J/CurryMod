package wtf.l4j.mixin.mixins;

import net.minecraft.client.render.RenderTickCounter;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.movement.Timer;

@Mixin(RenderTickCounter.class)
public class MixinRenderTickCounter {

    @Shadow public float lastFrameDuration;

    @Inject(method = "beginRenderTick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderTickCounter;prevTimeMillis:J", opcode = Opcodes.PUTFIELD))
    public void beginRenderTick(long timeMillis, CallbackInfoReturnable<Integer> ci) {
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(Timer.class).get().isEnabled()) {
            lastFrameDuration *= Timer.speed.getFloatValue();
        }
    }
}
