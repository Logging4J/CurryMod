package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionSlider;

@Hack.Construct(name = "Timer", description = "speeds up time", category = Hack.Category.MOVEMENT)
public class Timer extends Hack {

    public OptionSlider speed = new OptionSlider("Speed",0.1f,20f,0.1f,5f);
    public static Timer getInstance = new Timer();

    public Timer(){
        addOptions(speed);
    }

}
