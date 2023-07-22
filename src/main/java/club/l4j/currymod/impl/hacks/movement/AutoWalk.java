package club.l4j.currymod.impl.hacks.movement;

import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
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