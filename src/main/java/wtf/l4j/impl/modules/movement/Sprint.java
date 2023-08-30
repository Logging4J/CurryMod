package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import wtf.l4j.api.event.Event;
import wtf.l4j.api.event.events.GameTickEvent;
import wtf.l4j.api.event.events.TickEvent;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.newevent.GameTickListener;
import wtf.l4j.api.utils.MovementUtils;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "Sprint", desc = "Auto run", category = Category.MOVEMENT)
public class Sprint extends Module implements GameTickListener {

    public OptionMode mode = new OptionMode("Mode", "Rage", "Rage", "Legit");

    public Sprint() {
        addOptions(mode);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if (mode.isMode("Rage")) {
            mc.player.setSprinting(true);
        } else {
            if(MovementUtils.isMoving() && !mc.player.isSneaking()){
                mc.player.setSprinting(true);
            }
        }
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE +mode.getMode()+TextUtil.GRAY+"]";
    }

}
