package wtf.l4j.impl.modules.movement;

import com.ibm.icu.util.CodePointTrie;
import de.florianmichael.dietrichevents2.DietrichEvents2;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.utils.MovementUtils;

@ModuleInfo(name = "FastStop", desc = "like csgo", category = Category.MOVEMENT)
public class FastStop extends Module implements GameTickListener {


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
        if(!MovementUtils.isMoving()){
            mc.player.setVelocity(0,mc.player.getVelocity().y,0);
        }
    }

}
