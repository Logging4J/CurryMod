package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionSlider;
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
