package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.newevent.GameTickListener;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "Flight", desc = "fly hacky", category = Category.MOVEMENT)
public class Flight extends Module implements GameTickListener {

    public OptionMode mode = new OptionMode("Mode", "Creative", "Creative", "JetPack");
    public OptionSlider speed = new OptionSlider("Speed", 1f, 10f, 1f, 5f);

    public Flight() {
        addOptions(mode, speed);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
        if (mode.isMode("Creative")) {
            if (mc.player.isOnGround()) {
                mc.player.jump();
            }
            mc.player.getAbilities().allowFlying = true;
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().setFlySpeed(speed.getFloatValue() / 10f);
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        if (!mc.player.isCreative()) {
            mc.player.getAbilities().allowFlying = false;
        }
        mc.player.getAbilities().flying = false;
        mc.player.getAbilities().setFlySpeed(0.05f);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if (mode.isMode("JetPack") && mc.options.jumpKey.isPressed()) {
            mc.player.jump();
        }
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE +mode.getMode()+TextUtil.GRAY+"]";
    }
}
