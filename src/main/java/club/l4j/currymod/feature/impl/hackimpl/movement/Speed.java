package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.feature.options.impl.OptionSlider;
import club.l4j.currymod.util.player.MovementUtils;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "Speed", description = "The speed of light", category = Hack.Category.MOVEMENT)
public class Speed extends Hack {

    public OptionBoolean jump = new OptionBoolean("Jump", true);
    public OptionSlider strafe = new OptionSlider("StrafeVal",0.01,1,0.01,0.5);

    public Speed(){
        addOptions(jump, strafe);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if(MovementUtils.isMoving()){
            MovementUtils.strafe(strafe.getFloatValue());
            if (mc.player.isOnGround() && jump.isEnabled()) {
                mc.player.jump();
            }
        }
    }
}
