package dev.l4j.currymod.client.module.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.client.module.option.options.OptionNumber;
import dev.l4j.currymod.listener.IGameTickListener;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

@Module.Info(name = "Flight", description = "Fly like a bird", category = Module.Category.MOVEMENT)
public class Flight extends Module implements IGameTickListener {

    private final OptionNumber<Integer> speed = new OptionNumber<>("Speed", 5,1,20,1);
    private final OptionBoolean antiKick = new OptionBoolean("AntiKick", true);

    @Override
    protected void onEnable() {
        if (nullCheck()) return;

        DietrichEvents2.global().subscribe(GameTickEvent.ID, this);
        mc.player.getAbilities().allowFlying = true;
    }

    @Override
    protected void onDisable() {
        if (nullCheck()) return;

        DietrichEvents2.global().unsubscribe(GameTickEvent.ID, this);
        if (!mc.player.isCreative()) mc.player.getAbilities().allowFlying = false;
    }

    @Override
    public void onGameTick() {
        if (nullCheck()) return;

        Vec3d forward = (new Vec3d(0.0, 0.0, speed.getValue())).rotateY(-((float)Math.toRadians(mc.player.getYaw())));
        Vec3d strafe = forward.rotateY((float)Math.toRadians(90.0));

        if (antiKick.getValue()) {
            if (mc.player.age % 20 == 0) {
                send(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY() - 0.069, mc.player.getZ(), false, mc.player.horizontalCollision));
                send(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY() + 0.069, mc.player.getZ(), true, mc.player.horizontalCollision));
            }
        }

        mc.player.setVelocity(Vec3d.ZERO);

        if (mc.options.jumpKey.isPressed()) {
            mc.player.setVelocity(mc.player.getVelocity().add(0 , speed.getValue(), 0));
        }

        if (mc.options.sneakKey.isPressed()) {
            mc.player.setVelocity(mc.player.getVelocity().add(0 , -speed.getValue(), 0));
        }

        if (mc.options.backKey.isPressed()) {
            mc.player.setVelocity(mc.player.getVelocity().add(-forward.x , 0, -forward.z));
        }

        if (mc.options.forwardKey.isPressed()) {
            mc.player.setVelocity(mc.player.getVelocity().add(forward.x , 0, forward.z));
        }

        if (mc.options.leftKey.isPressed()) {
            mc.player.setVelocity(mc.player.getVelocity().add(strafe.x , 0, strafe.z));
        }

        if (mc.options.rightKey.isPressed()) {
            mc.player.setVelocity(mc.player.getVelocity().add(-strafe.x , 0, -strafe.z));
        }
    }

    @Override
    public String getDisplayInfo() {
        return speed.getValue().toString();
    }
}
