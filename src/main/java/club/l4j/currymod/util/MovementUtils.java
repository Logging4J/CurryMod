//MovementUtils By Logging4J :^)
package club.l4j.currymod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;

public class MovementUtils {
    public static MinecraftClient mc = MinecraftClient.getInstance();

    public static boolean isMoving() {
        return mc.player.input.movementForward != 0F || mc.player.input.movementSideways != 0F;
    }

    public static double getSpeed() {
        return Math.hypot(mc.player.getVelocity().x, mc.player.getVelocity().z);
    }

    public void strafe() {
        strafe(getSpeed());
    }

    public static void strafe(final float speed) {
        if (isMoving()) {
            final double yaw = lookDir();
            mc.player.setVelocity(-MathHelper.sin((float) yaw) * speed, mc.player.getVelocity().y, MathHelper.cos((float) yaw) * speed);
        }
    }

    public static void strafe(final double speed) {
        if (isMoving()) {
            final double yaw = lookDir();
            mc.player.setVelocity(-MathHelper.sin((float) yaw) * speed, mc.player.getVelocity().y, MathHelper.cos((float) yaw) * speed);
        }

    }

    public static double lookDir() {
        float forward = 1F;
        float rotationYaw = mc.player.getYaw();
        if (mc.player.input.movementForward < 0F){ rotationYaw += 180F;}
        if (mc.player.input.movementForward < 0F) forward = -0.5F;
        else if (mc.player.input.movementForward > 0F) forward = 0.5F;
        if (mc.player.input.movementSideways > 0F) rotationYaw -= 90F * forward;
        if (mc.player.input.movementSideways < 0F) rotationYaw += 90F * forward;
        return Math.toRadians(rotationYaw);
    }
}
