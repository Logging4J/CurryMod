package dev.logging4j.currymod.mixin;

import com.mojang.authlib.GameProfile;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.modules.misc.PlayerProtect;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListEntry.class)
public abstract class PlayerListEntryMixin {

    @Shadow public abstract GameProfile getProfile();

    @Inject(
            method = "getSkinTextures",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getSkinTexturesHEAD(CallbackInfoReturnable<SkinTextures> cir) {
        PlayerProtect playerProtect = CurryMod.getModuleManager().getModule(PlayerProtect.class);

        if (playerProtect != null && playerProtect.isEnabled() && playerProtect.getHideSkin().getValue()) {
            cir.setReturnValue(DefaultSkinHelper.getSkinTextures(getProfile()));
        }
    }
}
