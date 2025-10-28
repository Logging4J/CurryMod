package dev.l4j.currymod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.visual.ExtraTab;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerListHud.class)
public abstract class PlayerListHudMixin {

    @Shadow protected abstract List<PlayerListEntry> collectPlayerEntries();

    @Shadow @Final private MinecraftClient client;

    @ModifyConstant(
            constant = @Constant(longValue = 80L),
            method = "collectPlayerEntries"
    )
    private long modifyConstant$collectPlayerEntries(long count) {
        ExtraTab extraTab = CurryMod.INSTANCE.moduleManager.getModule(ExtraTab.class);

        return extraTab.isEnabled() ? extraTab.getSize().getValue() : count;
    }

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;min(II)I",
                    shift = At.Shift.BEFORE
            )
    )
    private void renderINVOKE$min(CallbackInfo ci,  @Local(ordinal = 5) LocalIntRef o, @Local(ordinal = 6)LocalIntRef p) {
        ExtraTab extraTab = CurryMod.INSTANCE.moduleManager.getModule(ExtraTab.class);

        if (!extraTab.isEnabled()) return;

        int newO;
        int newP = 1;
        int totalPlayers = newO = this.collectPlayerEntries().size();

        while (newO > extraTab.getHeight().getValue()) {
            newO = (totalPlayers + ++newP - 1) / newP;
        }

        o.set(newO);
        p.set(newP);
    }

    @Inject(
            method = "getPlayerName",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getPlayerNameHEAD(PlayerListEntry entry, CallbackInfoReturnable<Text> cir) {
        ExtraTab extraTab = CurryMod.INSTANCE.moduleManager.getModule(ExtraTab.class);

        if (extraTab.isEnabled()) {
            String name = entry.getProfile().getName();

            if (extraTab.getHighlightSelf().getValue() && name.equals(client.player.getName().getString())) {
                cir.setReturnValue(Text.literal(name).formatted(Formatting.LIGHT_PURPLE));
            }
        }
    }
}
