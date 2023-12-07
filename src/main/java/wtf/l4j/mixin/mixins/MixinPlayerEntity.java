package wtf.l4j.mixin.mixins;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.entity.player.PlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.l4j.CurryMod;
import wtf.l4j.api.event.DismountListener;
import wtf.l4j.api.event.JumpListener;
import wtf.l4j.impl.modules.visual.FreeCam;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;noClip:Z", opcode = Opcodes.PUTFIELD))
    public void noClipHook(PlayerEntity playerEntity, boolean value) {
        if (CurryMod.getInstance().getManagers().getModuleManager().getModule(FreeCam.class).isEnabled() && !playerEntity.isOnGround()) {
            playerEntity.noClip = true;
        } else {
            playerEntity.noClip = playerEntity.isSpectator();
        }
    }

    @Inject(method = "jump", at = @At("HEAD"))
    public void jump(CallbackInfo ci){
        JumpListener.JumpEvent jumpEvent = new JumpListener.JumpEvent();
        //@formatter:off
        DietrichEvents2.global().postInternal(JumpListener.JumpEvent.ID, jumpEvent);
        //@formatter:on
        if(jumpEvent.isCancelled()){
            ci.cancel();
        }

    }

}
