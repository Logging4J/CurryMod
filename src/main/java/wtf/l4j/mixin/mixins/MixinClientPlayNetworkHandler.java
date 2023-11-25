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

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.managers.CommandManager;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.event.ChatListener;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.event.Type;
import wtf.l4j.api.utils.ClientInfoInterface;
import wtf.l4j.api.utils.MinecraftInterface;

import static wtf.l4j.api.utils.text.TextUtil.*;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class MixinClientPlayNetworkHandler implements ClientInfoInterface, MinecraftInterface {

    @Shadow public abstract void sendChatMessage(String content);

    @Unique private boolean ignoreMsg;

    @Inject(method = "sendPacket(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true)
    public void sendPacket(Packet<?> packet, CallbackInfo ci){
        PacketListener.PacketEvent packetEvent = new PacketListener.PacketEvent(packet, Type.OUTGOING);
        //@formatter:off
        DietrichEvents2.global().postInternal(PacketListener.PacketEvent.ID, packetEvent);
        //@formatter:on
        if(packetEvent.isCancelled()){
            ci.cancel();
        }
    }

    @Inject(method = "onGameJoin", at = @At("TAIL"))
    public void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        mc.player.sendMessage(Text.of(GRAY + "[" + PURPLE + clientName + GRAY + "] " + WHITE + "Welcome to " + PURPLE + clientName + WHITE + " the default prefix is " + GREEN + "';'" + WHITE +" and default bind for ClickGui is "+GREEN+"RSHIFT"));
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
            CurryMod.getInstance().getManagers().getCommandManager().runCommand(content);
            mc.inGameHud.getChatHud().addToMessageHistory(content);
            ci.cancel();
        }
    }
}
