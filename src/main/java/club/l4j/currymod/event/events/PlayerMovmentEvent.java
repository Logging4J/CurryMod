package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;

public class PlayerMovmentEvent extends Event {

    private MovementType type;
    private Vec3d vec;

    public PlayerMovmentEvent(MovementType type, Vec3d vec) {
        this.type = type;
        this.vec = vec;
    }

    public MovementType getType() {
        return type;
    }

    public Vec3d getVec() {
        return vec;
    }
}
