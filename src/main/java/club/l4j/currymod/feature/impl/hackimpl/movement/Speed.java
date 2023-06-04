package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.util.player.MovementUtils;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "Speed", description = "The speed of light", category = Hack.Category.MOVEMENT)
public class Speed extends Hack {

    OptionMode mode = new OptionMode("Mode","Strafe","Strafe");

    public Speed(){
        addOptions(mode);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if(MovementUtils.isMoving()){
            if(mode.isMode("Strafe")) {
                MovementUtils.strafe(1);
                if (mc.player.isOnGround()) {
                    mc.player.jump();
                }
            }
        }
    }

    @Override
    public String getContent() {
        return "["+mode.getMode()+"]";
    }
}
