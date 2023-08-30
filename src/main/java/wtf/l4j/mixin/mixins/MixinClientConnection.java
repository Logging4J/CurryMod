package wtf.l4j.mixin.mixins;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.api.event.Type;

@Mixin(ClientConnection.class)
public class MixinClientConnection {

    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        wtf.l4j.api.event.PacketListener.PacketEvent packetEvent = new wtf.l4j.api.event.PacketListener.PacketEvent(packet, Type.INCOMING);
        //@formatter:off
        DietrichEvents2.global().postInternal(wtf.l4j.api.event.PacketListener.PacketEvent.ID, packetEvent);
        //@formatter:on
        if (packetEvent.isCancelled()) {
            ci.cancel();
        }
    }
}
