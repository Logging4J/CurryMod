package club.l4j.currymod.impl.hacks.movement;

import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionBoolean;
import club.l4j.currymod.core.hack.options.impl.OptionMode;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "AutoWalk", description = "Automatically Walks :)", category = Hack.Category.MOVEMENT)
public class AutoWalk extends Hack {
    public OptionMode mode = new OptionMode("Mode", "Simple", "Simple", "JumpWalk");
    public OptionBoolean sprint = new OptionBoolean("Sprint", true);

    public AutoWalk() {
        addOptions(mode, sprint);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if (mode.isMode("Simple")) {
            mc.options.forwardKey.setPressed(true);
            if (sprint.isEnabled()) mc.player.setSprinting(true);
        }
        if (mode.isMode("JumpWalk")) {
            mc.options.forwardKey.setPressed(true);
            mc.options.jumpKey.setPressed(true);
            if (sprint.isEnabled()) mc.player.setSprinting(true);
        }
    }

    @Override
    public void onDisable() {
        mc.options.forwardKey.setPressed(false);
        mc.options.jumpKey.setPressed(false);
        super.onDisable();
    }
}