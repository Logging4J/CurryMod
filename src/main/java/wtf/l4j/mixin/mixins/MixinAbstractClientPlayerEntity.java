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
import wtf.l4j.CurryMod;
import wtf.l4j.api.utils.Capes;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class MixinAbstractClientPlayerEntity extends PlayerEntity {

    public MixinAbstractClientPlayerEntity(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    private void onGetCapeTexture(CallbackInfoReturnable<Identifier> info) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(wtf.l4j.impl.modules.client.Capes.class).get().isEnabled()) {
            if (wtf.l4j.impl.modules.client.Capes.mode.isMode("cape1")) {
                info.setReturnValue(Capes.FRIEND_CAPE);
            }
            if (wtf.l4j.impl.modules.client.Capes.mode.isMode("cape2")) {
                info.setReturnValue(Capes.NN_CAPE);
            }
            if (wtf.l4j.impl.modules.client.Capes.mode.isMode("cape3")) {
                info.setReturnValue(Capes.WOW_CAPE);
            }
        }
    }
}