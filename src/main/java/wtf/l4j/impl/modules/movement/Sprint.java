package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.utils.MovementUtils;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "Sprint", desc = "Auto run", category = Category.MOVEMENT)
public class Sprint extends Module implements GameTickListener {

    private OptionMode mode = new OptionMode("Mode", "Rage", "Rage", "Legit");
    private OptionBoolean inWater = new OptionBoolean("InWater", true);

    public Sprint() {
        addOptions(mode, inWater);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if(mc.player.isInFluid() && !inWater.isEnabled()) return;
        if (mode.isMode("Rage")) {
            mc.player.setSprinting(true);
        } else {
            if(MovementUtils.isMoving()) {
                if (!mc.player.isSneaking()) {
                    mc.player.setSprinting(true);
                }
            }
        }
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE +mode.getMode()+TextUtil.GRAY+"]";
    }

}
