package wtf.l4j.mixin.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.l4j.api.auth.UserCapes;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.client.Capes;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class MixinAbstractClientPlayerEntity extends PlayerEntity {

    public MixinAbstractClientPlayerEntity(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    private void onGetCapeTexture(CallbackInfoReturnable<Identifier> info) {
        if (Managers.getModuleManager().getModule(Capes.class).get().isEnabled()) {
            if (Capes.mode.isMode("cape1")) {
                info.setReturnValue(UserCapes.FRIEND_CAPE);
            }
            if (Capes.mode.isMode("cape2")) {
                info.setReturnValue(UserCapes.NN_CAPE);
            }
            if (Capes.mode.isMode("cape3")) {
                info.setReturnValue(UserCapes.WOW_CAPE);
            }
        }
    }
}