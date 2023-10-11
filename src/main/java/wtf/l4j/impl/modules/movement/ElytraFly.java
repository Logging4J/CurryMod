package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "ElytraFly", desc = "Elytra go zoom zoom", category = Category.MOVEMENT)
public class ElytraFly extends Module implements GameTickListener {

    public static OptionMode mode = new OptionMode("Mode", "Bounce", "Bounce");

    public ElytraFly(){
        addOptions(mode);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
    }

    @Override
    public void onGameTick() {
        if(!mc.player.isFallFlying()) return;
        if(mc.player.isOnGround()) mc.player.jump();
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE + mode.getMode() + TextUtil.GRAY+"]";
    }

}
