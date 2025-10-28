package dev.l4j.currymod.client.rotation;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.listener.IPostPlayerTickListener;
import dev.l4j.currymod.listener.IPrePlayerTickListener;
import dev.l4j.currymod.util.MathUtils;
import dev.l4j.currymod.util.MinecraftInterface;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

@Getter @Setter
public class RotationManager implements MinecraftInterface, IPrePlayerTickListener, IPostPlayerTickListener {

    private float yaw, pitch, prevYaw, prevPitch;

    public RotationManager() {
        DietrichEvents2.global().subscribe(PrePlayerTickEvent.ID, this);
        DietrichEvents2.global().subscribe(PostPlayerTickEvent.ID, this);
    }

    public void updateRotations() {
        this.prevYaw = this.yaw;
        this.prevPitch = this.pitch;

        this.yaw = mc.player.getYaw();
        this.pitch = mc.player.getPitch();
    }

    public void restoreRotations() {
        mc.player.setYaw(yaw);
        mc.player.headYaw = yaw;
        mc.player.setPitch(pitch);
    }

    public void setPlayerRotations(float yaw, float pitch) {
        mc.player.setYaw(yaw);
        mc.player.headYaw = yaw;
        mc.player.setPitch(pitch);
    }

    public void setPlayerYaw(float yaw) {
        mc.player.setYaw(yaw);
        mc.player.headYaw = yaw;
    }

    public boolean isRotating() {
        return this.yaw != this.prevYaw || this.pitch != this.prevPitch;
    }


    public void lookAtPos(BlockPos pos) {
        float[] angle = MathUtils.calcAngle(mc.player.getEyePos(), new Vec3d((float) pos.getX() + 0.5f, (float) pos.getY() + 0.5f, (float) pos.getZ() + 0.5f));
        this.setPlayerRotations(angle[0], angle[1]);
    }

    public void lookAtVec3d(Vec3d vec3d) {
        float[] angle = MathUtils.calcAngle(mc.player.getEyePos(), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        this.setPlayerRotations(angle[0], angle[1]);
    }

    public void lookAtEntity(Entity entity) {
        float[] angle = MathUtils.calcAngle(mc.player.getEyePos(), entity.getEyePos());
        this.setPlayerRotations(angle[0], angle[1]);
    }

    @Override
    public void onPostPlayerTick() {
        restoreRotations();
    }

    @Override
    public void onPrePlayerTick() {
        updateRotations();
    }
}
