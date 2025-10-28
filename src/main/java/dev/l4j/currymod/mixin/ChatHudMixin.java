package dev.l4j.currymod.mixin;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.misc.ChatModifier;
import dev.l4j.currymod.listener.IMessageReceiveListener;
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

    @ModifyVariable(
            method = "render(Lnet/minecraft/client/gui/DrawContext;IIIZ)V",
            at = @At(value = "STORE"),
            ordinal = 2
    )
    private float renderSTORE2(float h) {
        ChatModifier chatModifier = CurryMod.INSTANCE.moduleManager.getModule(ChatModifier.class);
        return (chatModifier.isEnabled() && chatModifier.getClearChat().getValue()) ? 0.0F : h;
    }
}
