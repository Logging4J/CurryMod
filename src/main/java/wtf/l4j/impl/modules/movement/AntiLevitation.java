package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.entity.effect.StatusEffects;
import wtf.l4j.api.event.EffectListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;

@ModuleInfo(name = "AntiLevitation", desc = "prevents levitation", category = Category.MOVEMENT)
public class AntiLevitation extends Module implements EffectListener {

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(EffectEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(EffectEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onEffect(EffectEvent potionEvent) {
        if(potionEvent.getEntity() == mc.player && potionEvent.getStatusEffect() == StatusEffects.LEVITATION){
            potionEvent.setCancelled(true);
        }
    }
}
