package wtf.l4j.mixin.mixins;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.event.Type;

@Mixin(ClientCommonNetworkHandler.class)
public class MixinClientCommonNetworkHandler {

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    public void sendPacket(Packet<?> packet, CallbackInfo ci) {
        PacketListener.PacketEvent packetEvent = new PacketListener.PacketEvent(packet, Type.OUTGOING);
        //@formatter:off
        DietrichEvents2.global().postInternal(PacketListener.PacketEvent.ID, packetEvent);
        //@formatter:on
        if(packetEvent.isCancelled()){
            ci.cancel();
        }
    }
}
