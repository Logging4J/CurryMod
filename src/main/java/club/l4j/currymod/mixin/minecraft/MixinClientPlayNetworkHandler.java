package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.PacketSendEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class MixinClientPlayNetworkHandler {

    protected MinecraftClient mc = MinecraftClient.getInstance();


    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, CallbackInfo ci){
        PacketSendEvent event = new PacketSendEvent(packet);
        CurryMod.EVENT_BUS.call(event);
        if(event.isCancelled()){
            ci.cancel();
        }
    }
}
