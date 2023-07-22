package club.l4j.currymod.core.util.player;

import club.l4j.currymod.core.util.IGlobals;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.MathHelper;

public class MovementUtils implements IGlobals {

    public static boolean isMoving() {
        return mc.player.input.movementForward != 0F || mc.player.input.movementSideways != 0F;
    }

    public static double getSpeed() {
        return Math.hypot(mc.player.getVelocity().x, mc.player.getVelocity().z);
    }

    public void strafe() {
        strafe(getSpeed());
    }

    public static void strafe(float speed) {
        if (isMoving()) {
            final double yaw = lookDir();
            mc.player.setVelocity(-MathHelper.sin((float) yaw) * speed, mc.player.getVelocity().y, MathHelper.cos((float) yaw) * speed);
        }
    }

    public static void strafe(double speed) {
        if (isMoving()) {
            final double yaw = lookDir();
            mc.player.setVelocity(-MathHelper.sin((float) yaw) * speed, mc.player.getVelocity().y, MathHelper.cos((float) yaw) * speed);
        }

    }

    public static double lookDir() {
        float forward = 1F;
        float rotationYaw = mc.player.getYaw();

        if (mc.player.input.movementForward < 0F) {
            rotationYaw += 180F;
            forward = -0.5F;
        } else if (mc.player.input.movementForward > 0F) {
            forward = 0.5F;
        }

        if (mc.player.input.movementSideways > 0F) {
            rotationYaw -= 90F * forward;
        } else if (mc.player.input.movementSideways < 0F) {
            rotationYaw += 90F * forward;
        }

        return Math.toRadians(rotationYaw);
    }

    public static void centerPlayer() {
        double x = MathHelper.floor(mc.player.getX()) + 0.5;
        double z = MathHelper.floor(mc.player.getZ()) + 0.5;
        mc.player.setPosition(x, mc.player.getY(), z);
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY(), mc.player.getZ(), mc.player.isOnGround()));
    }

    public static float[] getRotationsToEntity(Entity e) {
        double deltaX = e.getX() + (e.getX() - e.prevX) - mc.player.getX();
        double deltaY = e.getY() - 3.5 + e.getEyeHeight(e.getPose()) - mc.player.getY() + mc.player.getEyeHeight(e.getPose());
        double deltaZ = e.getZ() + (e.getZ() - e.prevZ) - mc.player.getZ();
        double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
        float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ));
        float pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
        double v = Math.toDegrees(Math.atan(deltaZ / deltaX));
        if (deltaX < 0 && deltaZ < 0) {
            yaw = (float) (90 + v);
        } else if (deltaX > 0 && deltaZ < 0) {
            yaw = (float) (-90 + v);
        }
        return new float[] {yaw, pitch};
    }
}
