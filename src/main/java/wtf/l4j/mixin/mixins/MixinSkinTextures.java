package wtf.l4j.mixin.mixins;

import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.l4j.CurryMod;
import wtf.l4j.api.auth.UserCapes;
import wtf.l4j.impl.modules.client.Capes;

@Mixin(SkinTextures.class)
public class MixinSkinTextures {

    @Inject(method = "capeTexture", at = @At("HEAD"), cancellable = true)
    public void capeTexture(CallbackInfoReturnable<Identifier> cir) {
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(Capes.class).get().isEnabled()){
            if (Capes.mode.isMode("cape1")) {
                cir.setReturnValue(UserCapes.FRIEND_CAPE);
            }
            if (Capes.mode.isMode("cape2")) {
                cir.setReturnValue(UserCapes.NN_CAPE);
            }
            if (Capes.mode.isMode("cape3")) {
                cir.setReturnValue(UserCapes.WOW_CAPE);
            }
        }
    }
}
