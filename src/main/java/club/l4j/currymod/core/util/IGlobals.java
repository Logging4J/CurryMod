package club.l4j.currymod.core.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;
import net.minecraft.text.Text;

public interface IGlobals {

    MinecraftClient mc = MinecraftClient.getInstance();

    default void sendMsg(String s){
        mc.player.sendMessage(Text.of(TextUtil.WHITE + "["+ TextUtil.AQUA +"CurryMod.Club"+TextUtil.WHITE+"] "+ s));
    }

    default boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }


    default void sendWarningMsg(String s){
        mc.player.sendMessage(Text.of(TextUtil.WHITE + "["+ TextUtil.AQUA +"CurryMod.Club"+TextUtil.WHITE+"][" + TextUtil.RED +"WARNING"+TextUtil.WHITE+"] "+ s));
    }

    default void sendPacket(Packet<?> packet){
        mc.getNetworkHandler().sendPacket(packet);
    }

}
