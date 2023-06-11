package club.l4j.currymod.feature.impl.hackimpl.visual;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;

@Hack.Construct(name = "NoRender", description = "view stuff", category = Hack.Category.VISUAL)
public class NoRender extends Hack {

    public static NoRender getInstance = new NoRender();

    public OptionBoolean noBossBar = new OptionBoolean("NoBossBar", true);
    public OptionBoolean fire = new OptionBoolean("NoFireOverlay", true);
    public OptionBoolean liquid = new OptionBoolean("NoLiquidOverlay", true);
    public OptionBoolean portal = new OptionBoolean("NoPortalOverlay", true);
    public OptionBoolean snow = new OptionBoolean("NoSnowOverlay", true);
    public OptionBoolean hurt = new OptionBoolean("NoHurtCam", true);
    public OptionBoolean pumpkin = new OptionBoolean("NoPumpkin", true);
    public OptionBoolean fog = new OptionBoolean("NoFog", true);
    public OptionBoolean particles = new OptionBoolean("NoParticles", true);
    public OptionBoolean background = new OptionBoolean("NoGuiBack", true);


    public NoRender(){
        addOptions(noBossBar, fire, liquid, portal, snow, hurt, pumpkin, fog, particles, background);
    }
}
