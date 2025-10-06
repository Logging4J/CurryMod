package dev.l4j.currymod.client.module.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionMode;
import dev.l4j.currymod.client.module.option.options.OptionNumber;
import dev.l4j.currymod.listener.IPacketSendListener;
import dev.l4j.currymod.mixin.accessor.PlayerMoveC2SPacketAccessor;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Module.Info(name = "NoFall", description = "Prevents fall damage", category = Module.Category.MOVEMENT)
public class NoFall extends Module implements IPacketSendListener {

    private final OptionMode mode = new OptionMode("Mode", "Packet", "Packet");

    public NoFall() {
        addOptions(mode);
    }

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(PacketSendEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(PacketSendEvent.ID, this);
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (mc.player.getAbilities().creativeMode || !mode.getValue().equals("SetGround")) return;

        if (event.getPacket() instanceof PlayerMoveC2SPacket packet) {
            ((PlayerMoveC2SPacketAccessor) packet).access$onGround(true);
        }
    }

    @Override
    public String getDisplayInfo() {
        return mode.getValue();
    }

}
