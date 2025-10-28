package dev.l4j.currymod.client.module.modules.visual;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import lombok.Getter;

@Getter
@Module.Info(name = "NoRender", description = "Don't Render certain things", category = Module.Category.VISUAL)
public class NoRender extends Module {

    private final OptionBoolean hurtCam = new OptionBoolean("HurtCam", true);
    private final OptionBoolean fire = new OptionBoolean("Fire Overlay", true);
    private final OptionBoolean pumpkin = new OptionBoolean("Pumpkin Overlay", true);
    private final OptionBoolean powderSnow = new OptionBoolean("PowderSnow Overlay", true);
    private final OptionBoolean liquid = new OptionBoolean("Liquid Overlay", true);
    private final OptionBoolean spyGlass = new OptionBoolean("SpyGlass Overlay", true);
    private final OptionBoolean wall = new OptionBoolean("Wall Overlay", true);
    private final OptionBoolean portal = new OptionBoolean("Portal Overlay", true);
    private final OptionBoolean guiBackground = new OptionBoolean("GUI Background", true);
    private final OptionBoolean armor = new OptionBoolean("Armor", true);

    public NoRender() {
        addOptions(hurtCam, fire, pumpkin, powderSnow, liquid, wall, spyGlass, portal, guiBackground, armor);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
