package wtf.l4j.mixin.mixins;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.api.manager.managers.CommandManager;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.newevent.ChatListener;
import wtf.l4j.api.newevent.PacketListener;
import wtf.l4j.api.newevent.Type;

import static wtf.l4j.api.utils.text.TextUtil.*;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class MixinClientPlayNetworkHandler {

    @Shadow public abstract void sendChatMessage(String content);

    @Shadow @Final private MinecraftClient client;

    @Unique private boolean ignoreMsg;

    @Inject(method = "onGameJoin", at = @At("TAIL"))
    public void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        client.player.sendMessage(Text.of(GRAY + "[" + PURPLE +"CurryMod" + GRAY + "] " + WHITE + "Welcome to " + PURPLE + "CurryMod" + WHITE + " the default prefix is " + GREEN + "';'" + WHITE +" and default bind for ClickGui is "+GREEN+"RSHIFT"));
    }

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, CallbackInfo ci){
        PacketListener.PacketEvent packetEvent = new PacketListener.PacketEvent(packet, Type.OUTGOING);
        //@formatter:off
        DietrichEvents2.global().postInternal(PacketListener.PacketEvent.ID, packetEvent);
        //@formatter:on
        if(packetEvent.isCancelled()){
            ci.cancel();
        }
    }

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(String content, CallbackInfo ci) {
        if(ignoreMsg) return;
        if(!content.startsWith(CommandManager.PREFIX)) {
            ChatListener.ChatEvent chatEvent = new ChatListener.ChatEvent(content, Type.OUTGOING);
            //@formatter:off
            DietrichEvents2.global().postInternal(ChatListener.ChatEvent.ID, chatEvent);
            //@formatter:on
            if (!chatEvent.isCancelled()) {
                ignoreMsg = true;
                sendChatMessage(chatEvent.getMessage());
                ignoreMsg = false;
            }
            ci.cancel();
            return;
        }
        if(content.startsWith(CommandManager.PREFIX)) {
            Managers.getCommandManager().runCommand(content);
            client.inGameHud.getChatHud().addToMessageHistory(content);
            ci.cancel();
        }
    }
}
