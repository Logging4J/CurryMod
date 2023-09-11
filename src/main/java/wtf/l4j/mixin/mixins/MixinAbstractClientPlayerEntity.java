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

@Mixin(AbstractClientPlayerEntity.class)
public abstract class MixinAbstractClientPlayerEntity extends PlayerEntity {

    public MixinAbstractClientPlayerEntity(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    private void onGetCapeTexture(CallbackInfoReturnable<Identifier> info) {
        if(UserCapes.FRIEND_UUIDS.contains(getUuid().toString())){
            info.setReturnValue(UserCapes.FRIEND_CAPE);
        }else {
            info.setReturnValue(UserCapes.NN_CAPE);
        }
    }

}
