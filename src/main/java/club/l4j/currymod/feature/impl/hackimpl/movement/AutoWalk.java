package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "AutoWalk", description = "Automatically Walks :)", category = Hack.Category.MOVEMENT)
public class AutoWalk extends Hack {

    @DemoListen
    public void onTick(TickEvent e) {
        mc.options.forwardKey.setPressed(true);
    }

    @Override
    public void onDisable() {
        mc.options.forwardKey.setPressed(false);
        super.onDisable();
    }
}