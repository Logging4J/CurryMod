package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.impl.hackimpl.misc.ChatEdits;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChatHud.class)
public class MixinChatHud {

    @ModifyConstant(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", constant = @Constant(intValue = 100))
    int val(int constant){
        if(CurryMod.featureManager.getHack("ChatEdits").isEnabled() && ChatEdits.history.isEnabled()){
            //Big Number :O
            return Integer.MAX_VALUE;
        }else {
            return 100;
        }
    }
}
