package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.event.JumpListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.Option;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.utils.MovementUtils;

@ModuleInfo(name = "HighJump", desc = "jump higher", category = Category.MOVEMENT)
public class HighJump extends Module implements GameTickListener, JumpListener {

    OptionBoolean autoJump = new OptionBoolean("AutoJump", true);
    OptionSlider yVelo = new OptionSlider("VelocityY", 0.01,8,0.01,2.5);

    public HighJump(){
        addOptions(autoJump, yVelo);
    }

    private int ticks;

    @Override
    public void onEnable() {
        ticks = autoJump.isEnabled() ? 1 : 0;
        DietrichEvents2.global().subscribe(GameTickEvent.ID, this);
        DietrichEvents2.global().subscribe(JumpEvent.ID, this);
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickEvent.ID, this);
        DietrichEvents2.global().subscribe(JumpEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if (ticks == 0){
            return;
        }
        if (ticks <= 7) {
            ticks++;
            mc.player.setVelocity(mc.player.getVelocity().x, yVelo.getValue(), mc.player.getVelocity().z);
            if (ticks <= 2) {
                mc.player.setVelocity(mc.player.getVelocity().x, yVelo.getValue() + 0.05, mc.player.getVelocity().z);
            }
        } else {
            toggle();
        }
    }

    @Override
    public void onJump() {
        if(!autoJump.isEnabled()){
            ticks = 1;
        }
    }
}
