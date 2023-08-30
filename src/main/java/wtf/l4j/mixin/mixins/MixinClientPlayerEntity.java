package wtf.l4j.mixin.mixins;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.movement.NoSlow;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean redirectUsingItem(ClientPlayerEntity player) {
        if (Managers.getModuleManager().getModule(NoSlow.class).get().isEnabled()) {
            return false;
        }
        return player.isUsingItem();
    }


}
