package dev.logging4j.currymod.mixin;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.listener.IPacketReceiveListener;
import dev.logging4j.currymod.listener.IPacketSendListener;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BundleS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(
            method = "send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;Z)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void sendHEAD(Packet<?> packet, PacketCallbacks callbacks, boolean flush, CallbackInfo ci) {
        IPacketSendListener.PacketSendEvent event = new IPacketSendListener.PacketSendEvent(packet);
        DietrichEvents2.global().call(IPacketSendListener.PacketSendEvent.ID, event);

        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(
            method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/ClientConnection;handlePacket(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;)V",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true
    )
    private void channelRead0INVOKE$handlePacket(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo ci) {
        if (packet instanceof BundleS2CPacket bundle) {
            for (Iterator<Packet<? super ClientPlayPacketListener>> it = bundle.getPackets().iterator(); it.hasNext(); ) {
                IPacketReceiveListener.PacketReceiveEvent event = new IPacketReceiveListener.PacketReceiveEvent(it.next());
                DietrichEvents2.global().call(IPacketReceiveListener.PacketReceiveEvent.ID, event);

                if (event.isCancelled()) {
                    it.remove();
                }
            }
        } else {
            IPacketReceiveListener.PacketReceiveEvent event = new IPacketReceiveListener.PacketReceiveEvent(packet);
            DietrichEvents2.global().call(IPacketReceiveListener.PacketReceiveEvent.ID, event);

            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }
}
