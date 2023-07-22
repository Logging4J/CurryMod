package club.l4j.currymod.impl.hacks.visual;

import club.l4j.currymod.core.hack.Hack;

@Hack.Construct(name = "Xray", description = "See through walls n shit", category = Hack.Category.VISUAL)
public class Xray extends Hack {

    @Override
    public void onEnable() {
        mc.worldRenderer.reload();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.worldRenderer.reload();
        super.onDisable();
    }
}
