package club.l4j.currymod.impl.hacks.movement;

import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionSlider;

@Hack.Construct(name = "Timer", description = "speeds up time", category = Hack.Category.MOVEMENT)
public class Timer extends Hack {

    public static OptionSlider speed = new OptionSlider("Speed",0.1f,20f,0.1f,5f);

    public Timer(){
        addOptions(speed);
    }

}
