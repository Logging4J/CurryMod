package wtf.l4j.impl.modules.combat;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.entity.decoration.EndCrystalEntity;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionSlider;

import java.util.Comparator;
import java.util.stream.StreamSupport;

@ModuleInfo(name = "AutoCrystal(WIP)", desc = "Crystal go boob boob", category = Category.COMBAT)
public class AutoCrystal extends Module implements GameTickListener {

    private OptionSlider range = new OptionSlider("Range", 1, 10, 1, 3);

    public AutoCrystal(){
        addOptions(range);
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
        EndCrystalEntity closestCrystal = getClosestCrystal();
        if(closestCrystal != null && mc.player.distanceTo(closestCrystal) <= range.getIntValue()){

        }
    }

    private EndCrystalEntity getClosestCrystal(){
        return StreamSupport.stream(mc.world.getEntities().spliterator(), false)
                .filter(entity -> entity instanceof EndCrystalEntity)
                .map(entity -> ((EndCrystalEntity) entity))
                .min(Comparator.comparing(mc.player::distanceTo))
                .orElse(null);
    }
}
