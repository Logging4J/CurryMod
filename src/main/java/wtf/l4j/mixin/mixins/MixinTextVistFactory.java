package wtf.l4j.mixin.mixins;

import net.minecraft.text.TextVisitFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import wtf.l4j.CurryMod;
import wtf.l4j.api.utils.MinecraftInterface;
import wtf.l4j.impl.modules.misc.NameProtect;

@Mixin(TextVisitFactory.class)
public class MixinTextVistFactory implements MinecraftInterface {

    @ModifyArg(
            method = "visitFormatted(Ljava/lang/String;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z")
    )
    private static String modifyText(String text){
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(NameProtect.class).isEnabled()) return text.replace(mc.getSession().getUsername(), "CurryModUser");
        else return text;
    }


}
