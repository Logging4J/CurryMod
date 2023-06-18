package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.util.player.MovementUtils;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "Sprint", description = "sprint", category = Hack.Category.MOVEMENT)
public class Sprint extends Hack {

    public OptionMode mode = new OptionMode("Mode", "Rage", "Rage", "Omni");

    public Sprint() {
        addOptions(mode);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if (nullCheck()) {return;}
        if (mode.isMode("Rage")) {
            mc.player.setSprinting(true);
        } else if (mode.isMode("Omni")) {
            if ((mc.options.forwardKey.isPressed() || mc.options.leftKey.isPressed() || mc.options.rightKey.isPressed() || mc.options.backKey.isPressed()) && !mc.player.isSneaking()) {
                mc.player.setSprinting(true);
            }
        }
    }

    @Override
    public String getContent() {
        return "["+mode.getMode()+"]";
    }

}
