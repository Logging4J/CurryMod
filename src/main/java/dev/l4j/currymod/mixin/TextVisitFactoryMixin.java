package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.misc.PlayerProtect;
import dev.l4j.currymod.util.MinecraftInterface;
import net.minecraft.text.TextVisitFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TextVisitFactory.class)
public class TextVisitFactoryMixin implements MinecraftInterface {

    @ModifyArg(
            method = "visitFormatted(Ljava/lang/String;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z"
            )
    )
    private static String visitFormattedINVOKE$visitFormatted(String text) {
        PlayerProtect playerProtect = CurryMod.INSTANCE.moduleManager.getModule(PlayerProtect.class);

        if (playerProtect.isEnabled() && playerProtect.getHideName().getValue()) {
            return text.replace(mc.getSession().getUsername(), "CurryModUser");
        } else {
            return text;
        }
    }
}
