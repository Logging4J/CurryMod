package wtf.l4j.mixin.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.movement.NoSlow;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

    @ModifyExpressionValue(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean redirectUsingItem(boolean isUsingItem) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(NoSlow.class).get().isEnabled()){
            return false;
        }
        return isUsingItem;
    }

}
