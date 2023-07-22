package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.StopUsingItemEvent;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientInteractionManager {

    @Inject(method = "stopUsingItem" , at = @At("HEAD"), cancellable = true)
    public void stopUsingItem(PlayerEntity player, CallbackInfo ci) {
        StopUsingItemEvent e = new StopUsingItemEvent(player.getActiveItem().getItem());
        CurryMod.EVENT_BUS.call(e);
        if(e.isCancelled()){
            ci.cancel();
        }
    }
}
