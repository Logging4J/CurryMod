package wtf.l4j.mixin.mixins;

import net.minecraft.client.gui.hud.PlayerListHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.ExtraTab;

@Mixin(PlayerListHud.class)
public class MixinPlayerListHud {

    @ModifyArg(method = "collectPlayerEntries", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;limit(J)Ljava/util/stream/Stream;"))
    private long modifyCollectPlayerEntries(long maxSize){
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(ExtraTab.class).isEnabled()){
            return 500L;
        }
        return maxSize;
    }


}
