package wtf.l4j.mixin.mixins;

import net.minecraft.entity.passive.StriderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.misc.EntityControl;

@Mixin(StriderEntity.class)
public class MixinStriderEntity {

    @Inject(method = "isSaddled", at = @At("HEAD"), cancellable = true)
    public void isSaddled(CallbackInfoReturnable<Boolean> cir) {
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(EntityControl.class).isEnabled()){
            cir.setReturnValue(true);
        }
    }
}
