package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.impl.hackimpl.visual.NoRender;
import net.minecraft.client.gui.hud.BossBarHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BossBarHud.class})
public class MixinBossBarHud {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(CallbackInfo info) {
        if (CurryMod.featureManager.getHack("NoRender").isEnabled() && NoRender.getInstance.noBossBar.isEnabled()) {
            info.cancel();
        }
    }
}
