package dev.l4j.currymod.mixin;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.listener.IMessageSendListener;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Shadow
    public abstract void sendChatMessage(String content);

    @Unique
    private boolean ignoreChatMessage;

    @Inject(
            method = "sendChatMessage",
            at = @At("HEAD"),
            cancellable = true
    )
    public void sendChatMessageHEAD(String content, CallbackInfo ci) {
        if (ignoreChatMessage) return;

        IMessageSendListener.MessageSendEvent event = new IMessageSendListener.MessageSendEvent(content);
        DietrichEvents2.global().call(IMessageSendListener.MessageSendEvent.ID, event);

        if (event.isCancelled()) {
            ci.cancel();
        } else if(!event.getMessage().equals(content)) {
            ignoreChatMessage = true;
            sendChatMessage(event.getMessage());
            ignoreChatMessage = false;
            ci.cancel();
        }
    }
}
