package wtf.l4j.impl.modules.visual;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.entity.effect.StatusEffects;
import wtf.l4j.api.event.EffectListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;

@ModuleInfo(name = "NoRender", desc = "Choices for rendering", category = Category.VISUAL)
public class NoRender extends Module implements EffectListener {

    public static OptionBoolean fire = new OptionBoolean("NoFireOverlay", true);
    public static OptionBoolean liquid = new OptionBoolean("NoLiquidOverlay", true);
    public static OptionBoolean portal = new OptionBoolean("NoPortalOverlay", true);
    public static OptionBoolean snow = new OptionBoolean("NoSnowOverlay", true);
    public static OptionBoolean hurt = new OptionBoolean("NoHurtCam", true);
    public static OptionBoolean pumpkin = new OptionBoolean("NoPumpkin", true);
    public static OptionBoolean fog = new OptionBoolean("NoFog", true);
    public static OptionBoolean potion = new OptionBoolean("NoPotionHud", true);
    public static OptionBoolean dark = new OptionBoolean("NoDarkEffect", true);
    public static OptionBoolean blind = new OptionBoolean("NoBlindEffect", true);
    public static OptionBoolean nausea = new OptionBoolean("NoNauseaEffect", true);

    public NoRender(){
        addOptions(fire, liquid, portal, snow, hurt, pumpkin, fog, potion, dark, blind, nausea);
    }

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
        if(potionEvent.getEntity() == mc.player) {
            if (dark.isEnabled() && potionEvent.getStatusEffect() == StatusEffects.DARKNESS) {
                potionEvent.setCancelled(true);
            } else if (blind.isEnabled() && potionEvent.getStatusEffect() == StatusEffects.BLINDNESS) {
                potionEvent.setCancelled(true);
            } else if (nausea.isEnabled() && potionEvent.getStatusEffect() == StatusEffects.NAUSEA) {
                potionEvent.setCancelled(true);
            }
        }
    }
}
