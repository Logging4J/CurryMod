package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.*;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.event.Type;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.option.options.OptionBoolean;

import java.util.ArrayDeque;
import java.util.Queue;

@ModuleInfo(name = "Blink", desc = "Automatically Walks :)", category = Category.MOVEMENT)
public class Blink extends Module implements PacketListener {

    public static OptionBoolean playermovec2s = new OptionBoolean("PlayerMoveC2SPacket", true);
    public static OptionBoolean playerinteractblockc2s = new OptionBoolean("PlayerInteractBlockC2SPacket", true);
    public static OptionBoolean playerinteractentityc2s = new OptionBoolean("PlayerInteractEntityC2SPacket", true);
    public static OptionBoolean playeractionc2s = new OptionBoolean("PlayerActionC2SPacket", true);
    public static OptionBoolean playerinputc2s = new OptionBoolean("PlayerInputC2SPacket", true);

    public Blink(){
            addOptions(playermovec2s, playerinteractblockc2s, playerinteractentityc2s, playeractionc2s, playerinputc2s);
        }private final Queue<Packet<?>> packetQueue = new ArrayDeque<>();


    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(PacketListener.PacketEvent.ID, this);
    }

    @Override
    public void onDisable() {
        super.onDisable();

        Packet<?> pack;
        while ((pack = packetQueue.poll()) != null) {
            assert mc.player != null;
            mc.player.networkHandler.getConnection().send(pack, null);
        }
    }

    @Override
    public void onPacket(PacketEvent event) {
        if(event.getType() == Type.INCOMING) {
            if (!isEnabled()) {
                return;
            }

            if (isPacketDelayed(event.getPacket())) {
                packetQueue.add(event.getPacket());
            }
        }

    }

    private static boolean isPacketDelayed(Packet<?> packet) {
        return packet instanceof PlayerMoveC2SPacket && playermovec2s.isEnabled()
                || packet instanceof PlayerInteractBlockC2SPacket && playerinteractblockc2s.isEnabled()
                || packet instanceof PlayerInteractEntityC2SPacket && playerinteractentityc2s.isEnabled()
                || packet instanceof PlayerActionC2SPacket && playeractionc2s.isEnabled()
                || packet instanceof PlayerInputC2SPacket && playerinputc2s.isEnabled();}
}