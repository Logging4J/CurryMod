package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.feature.core.Hack;
import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.EntityPose;

@Hack.Construct(name = "AutoSwim", description = "auto swim!", category = Hack.Category.MOVEMENT)
public class AutoSwim extends Hack {

    @Subscribe
    public void onTick() {
        if (mc.player.isTouchingWater())
            mc.player.setPose(EntityPose.SWIMMING);
    }

    /*@Override
    public String getContent() {
        return "["+mode.getMode()+"]";*/
}
