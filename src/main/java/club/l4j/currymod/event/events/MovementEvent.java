package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;

public class MovementEvent extends Event {

    private MovementType type;
    private Vec3d vec;

    public MovementEvent(MovementType type, Vec3d vec) {
        this.type = type;
        this.vec = vec;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public Vec3d getVec() {
        return vec;
    }

    public void setVec(Vec3d vec) {
        this.vec = vec;
    }
}
