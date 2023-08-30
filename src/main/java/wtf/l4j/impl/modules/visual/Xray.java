package wtf.l4j.impl.modules.visual;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;

@ModuleInfo(name = "Xray", desc = "See ores through walls", category = Category.VISUAL)
public class Xray extends Module {

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
