package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.event.Type;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.utils.MovementUtils;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "HighJump", desc = "jump higher", category = Category.MOVEMENT)
public class HighJump extends Module implements GameTickListener, PacketListener{

    private final OptionMode mode = new OptionMode("Mode", "Grim", "Grim");
    private int ticks;

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(PacketListener.PacketEvent.ID, this);
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
        ticks = 0;
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(PacketListener.PacketEvent.ID, this);
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if (ticks == 0) return;
        if (ticks <= 7) {
            ticks++;
            mc.player.setVelocity(mc.player.getVelocity().x, 2.0, mc.player.getVelocity().z);
            MovementUtils.strafe(0.875);
            if (ticks <= 2) {
                mc.player.setVelocity(mc.player.getVelocity().x, 2.05, mc.player.getVelocity().z);
                MovementUtils.strafe(0.9);
            }
        }
    }

    @Override
    public void onPacket(PacketEvent packetEvent) {
        if (packetEvent.getType() == Type.OUTGOING) {
            if (packetEvent.getPacket() instanceof PlayerMoveC2SPacket.LookAndOnGround && ticks == 0) {
                ticks = 1;
            }
        }
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY + "[" + TextUtil.WHITE + mode.getMode() + TextUtil.GRAY + "]";
    }
}
