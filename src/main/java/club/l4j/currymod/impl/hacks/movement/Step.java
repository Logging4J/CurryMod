package club.l4j.currymod.impl.hacks.movement;

import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionSlider;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "Step", description = "Allows you to step up blocks", category = Hack.Category.MOVEMENT)
public class Step extends Hack {

    public OptionSlider height = new OptionSlider("Height", 0.5, 3, 0.5,1);

    public Step(){
        addOptions(height);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if(nullCheck()) return;
        mc.player.setStepHeight(height.getFloatValue());
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.player.setStepHeight(0.5f);
        super.onDisable();
    }
}
