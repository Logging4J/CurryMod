package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "AutoWalk", desc = "Automatically Walks :)", category = Category.MOVEMENT)
public class AutoWalk extends Module implements GameTickListener {

    public OptionMode mode = new OptionMode("Mode", "Simple", "Simple", "JumpWalk");

    public AutoWalk() {
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
        mc.options.forwardKey.setPressed(false);
        mc.options.jumpKey.setPressed(false);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if (mode.isMode("Simple")) {
            mc.options.forwardKey.setPressed(true);
        }
        if (mode.isMode("JumpWalk")) {
            mc.options.forwardKey.setPressed(true);
            mc.options.jumpKey.setPressed(true);
        }
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE +mode.getMode()+TextUtil.GRAY+"]";
    }

}
