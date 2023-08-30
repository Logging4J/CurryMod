package wtf.l4j.impl.modules.visual;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;

@ModuleInfo(name = "NoRender", desc = "Choices for rendering", category = Category.VISUAL)
public class NoRender extends Module {

    public static OptionBoolean fire = new OptionBoolean("NoFireOverlay", true);
    public static OptionBoolean liquid = new OptionBoolean("NoLiquidOverlay", true);
    public static OptionBoolean portal = new OptionBoolean("NoPortalOverlay", true);
    public static OptionBoolean snow = new OptionBoolean("NoSnowOverlay", true);
    public static OptionBoolean hurt = new OptionBoolean("NoHurtCam", true);
    public static OptionBoolean pumpkin = new OptionBoolean("NoPumpkin", true);
    public static OptionBoolean fog = new OptionBoolean("NoFog", true);
    public static OptionBoolean potion = new OptionBoolean("NoPotionHud", true);
    public static OptionBoolean armor = new OptionBoolean("NoArmor", true);


    public NoRender(){
        addOptions(fire, liquid, portal, snow, hurt, pumpkin, fog, potion, armor);
    }

}
