package wtf.l4j.mixin.mixins;

import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.l4j.CurryMod;
import wtf.l4j.api.utils.Capes;
import wtf.l4j.impl.modules.client.CapesModule;

@Mixin(SkinTextures.class)
public class MixinSkinTextures {

    @Inject(method = "capeTexture", at = @At("HEAD"), cancellable = true)
    public void capeTexture(CallbackInfoReturnable<Identifier> cir) {
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(CapesModule.class).isEnabled()){
            if (CapesModule.mode.isMode("cape1")) {
                cir.setReturnValue(Capes.FRIEND_CAPE);
            }
            if (CapesModule.mode.isMode("cape2")) {
                cir.setReturnValue(Capes.NN_CAPE);
            }
            if (CapesModule.mode.isMode("cape3")) {
                cir.setReturnValue(Capes.WOW_CAPE);
            }
        }
    }


}
