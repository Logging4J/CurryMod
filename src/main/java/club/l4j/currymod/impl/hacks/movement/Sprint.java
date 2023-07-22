package club.l4j.currymod.impl.hacks.movement;

import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionMode;
import club.l4j.currymod.core.util.player.MovementUtils;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "Sprint", description = "sprint", category = Hack.Category.MOVEMENT)
public class Sprint extends Hack {

    public OptionMode mode = new OptionMode("Mode", "Rage", "Rage", "Legit");

    public Sprint() {
        addOptions(mode);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if (nullCheck()) return;
        if (mode.isMode("Rage")) {
            mc.player.setSprinting(true);
        } else if (mode.isMode("Legit")) {
            if(MovementUtils.isMoving() && !mc.player.isSneaking()){
                mc.player.setSprinting(true);
            }
        }
    }

    @Override
    public String getContent() {
        return "["+mode.getMode()+"]";
    }

}
