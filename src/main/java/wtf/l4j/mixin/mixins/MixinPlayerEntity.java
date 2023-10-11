package wtf.l4j.mixin.mixins;

import net.minecraft.entity.player.PlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import wtf.l4j.CurryMod;
import wtf.l4j.impl.modules.visual.FreeCam;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;noClip:Z", opcode = Opcodes.PUTFIELD))
    public void noClipHook(PlayerEntity playerEntity, boolean value) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(FreeCam.class).get().isEnabled() && !playerEntity.isOnGround()) {
            playerEntity.noClip = true;
        } else {
            playerEntity.noClip = playerEntity.isSpectator();
        }
    }

}
