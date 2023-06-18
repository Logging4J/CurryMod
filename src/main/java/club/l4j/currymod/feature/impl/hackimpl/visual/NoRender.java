package club.l4j.currymod.feature.impl.hackimpl.visual;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;

@Hack.Construct(name = "NoRender", description = "view stuff", category = Hack.Category.VISUAL)
public class NoRender extends Hack {

    public static OptionBoolean noBossBar = new OptionBoolean("NoBossBar", true);
    public static OptionBoolean fire = new OptionBoolean("NoFireOverlay", true);
    public static OptionBoolean liquid = new OptionBoolean("NoLiquidOverlay", true);
    public static OptionBoolean portal = new OptionBoolean("NoPortalOverlay", true);
    public static OptionBoolean snow = new OptionBoolean("NoSnowOverlay", true);
    public static OptionBoolean hurt = new OptionBoolean("NoHurtCam", true);
    public static OptionBoolean pumpkin = new OptionBoolean("NoPumpkin", true);
    public static OptionBoolean fog = new OptionBoolean("NoFog", true);
    public static OptionBoolean particles = new OptionBoolean("NoParticles", true);
    public static OptionBoolean background = new OptionBoolean("NoGuiBack", true);


    public NoRender(){
        addOptions(noBossBar, fire, liquid, portal, snow, hurt, pumpkin, fog, particles, background);
    }
}
