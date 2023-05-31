package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.util.MovementUtils;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "Sprint", description = "sprint", category = Hack.Category.MOVEMENT)
public class Sprint extends Hack {

    OptionMode mode = new OptionMode("Mode", "Rage", "Rage", "Omni");

    public Sprint() {
        addOptions(mode);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if (nullCheck()) {
            return;
        }
        if (mode.isMode("Rage")) {
            mc.player.setSprinting(true);
        } else {
            if (MovementUtils.isMoving() && !mc.player.isSneaking()) {
                mc.player.setSprinting(true);
            }
            if (mode.isMode("Omni")) {
                if (mc.options.forwardKey.isPressed()) {
                    mc.player.setSprinting(true);
                }
                if (mc.options.leftKey.isPressed()) {
                    mc.player.setSprinting(true);
                }
                if (mc.options.rightKey.isPressed()) {
                    mc.player.setSprinting(true);
                }
                if (mc.options.backKey.isPressed()) {
                    mc.player.setSprinting(true);
                }
            }
        }
    }
}
