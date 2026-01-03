package dev.logging4j.currymod.mixin;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.listener.IMessageReceiveListener;
import dev.logging4j.currymod.module.modules.misc.ChatModifier;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {

    @Shadow public abstract void addMessage(Text message, @Nullable MessageSignatureData signatureData, @Nullable MessageIndicator indicator);
    @Unique private boolean skipOnAddMessage;

    @Inject(
            method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void addMessageHEAD(Text message, MessageSignatureData signatureData, MessageIndicator indicator, CallbackInfo ci) {
        if (skipOnAddMessage) return;

        IMessageReceiveListener.MessageReceiveEvent event = new IMessageReceiveListener.MessageReceiveEvent(message);
        DietrichEvents2.global().call(IMessageReceiveListener.MessageReceiveEvent.ID, event);

        if (event.isCancelled()){
            ci.cancel();
        } else {
            if (event.isModified()) {
                ci.cancel();

                skipOnAddMessage = true;
                addMessage(event.getMessage(), signatureData, indicator);
                skipOnAddMessage = false;
            }
        }
    }

    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"
            ),
            index = 4
    )
    private int modifyChatBackgroundColor(int color) {
        ChatModifier chatModifier =
                CurryMod.getModuleManager().getModule(ChatModifier.class);

        if (chatModifier.isEnabled() && chatModifier.getClearChat().getValue()) {
            return color & 0x00FFFFFF; // strip alpha (transparent bg)
        }

        return color;
    }

}
