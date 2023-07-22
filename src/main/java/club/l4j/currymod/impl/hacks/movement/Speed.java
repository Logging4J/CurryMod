package club.l4j.currymod.impl.hacks.movement;

import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionBoolean;
import club.l4j.currymod.core.hack.options.impl.OptionSlider;
import club.l4j.currymod.core.util.player.MovementUtils;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "Speed", description = "The speed of light", category = Hack.Category.MOVEMENT)
public class Speed extends Hack {

    public OptionBoolean jump = new OptionBoolean("Jump", true);
    public OptionSlider strafe = new OptionSlider("StrafeVal",0.01,2,0.01,0.5);

    public Speed(){
        addOptions(jump, strafe);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if(nullCheck() || !MovementUtils.isMoving()){return;}
        MovementUtils.strafe(strafe.getFloatValue());
        if (mc.player.isOnGround() && jump.isEnabled()) {
            mc.player.jump();
        }
    }
}
