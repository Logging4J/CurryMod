package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;

@Setter @Getter
public class MovementEvent extends Event {

    private MovementType type;
    private Vec3d vec;

    public MovementEvent(MovementType type, Vec3d vec) {
        this.type = type;
        this.vec = vec;
    }
}
