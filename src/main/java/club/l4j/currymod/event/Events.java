package club.l4j.currymod.event;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.KeyPressEvent;
import club.l4j.currymod.event.events.PacketReceiveEvent;
import club.l4j.currymod.event.events.PacketSendEvent;
import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.graphics.spoofgui.SpoofScreen;
import club.l4j.currymod.mixin.minecraft.ICustomPayloadC2SPacket;
import club.l4j.currymod.util.IGlobals;
import demo.knight.demobus.event.DemoListen;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.network.packet.c2s.play.ResourcePackStatusC2SPacket;
import net.minecraft.network.packet.s2c.play.ResourcePackSendS2CPacket;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class Events implements IGlobals {

    protected MinecraftClient mc = MinecraftClient.getInstance();
    public static boolean bypassResourcePack = false;

    @DemoListen
    public void onKey(KeyPressEvent e){
        if(mc.currentScreen == null) {
            if (e.getAction() == GLFW.GLFW_PRESS) {
                for (Hack hack : CurryMod.featureManager.hacks) {
                    if (e.getKey() == hack.getKey()) {
                        hack.toggle();
                    }
                }
            }
        }
    }

    @DemoListen
    public void onPacketSend(PacketSendEvent e){
        if(e.getPacket() instanceof ChatMessageC2SPacket p) {
            if (p.chatMessage().startsWith(Command.getPrefix())) {
                e.setCanceled(true);
                CurryMod.featureManager.runCommand(p.chatMessage());
            }
        }
        if(e.getPacket() instanceof CustomPayloadC2SPacket p){
            if(!SpoofScreen.channelString.isEmpty()){
                ((ICustomPayloadC2SPacket) p).setChannel(new Identifier(SpoofScreen.channelString));
            }
            if(!SpoofScreen.dataString.isEmpty()){
                if(SpoofScreen.asBytes){
                    ((ICustomPayloadC2SPacket) p).setData(new PacketByteBuf(Unpooled.wrappedBuffer(SpoofScreen.dataString.getBytes())));
                }else {
                    ((ICustomPayloadC2SPacket) p).setData(new PacketByteBuf(Unpooled.buffer()).writeString(SpoofScreen.dataString));
                }
            }
        }
    }

    @DemoListen
    public void onPacketReceive(PacketReceiveEvent e){
        if (e.getPacket() instanceof ResourcePackSendS2CPacket p) {
            sendMsg("Resource Pack Bypassed!");
            e.setCanceled(true);
            sendPacket(new ResourcePackStatusC2SPacket(ResourcePackStatusC2SPacket.Status.ACCEPTED));
        }
    }
}
