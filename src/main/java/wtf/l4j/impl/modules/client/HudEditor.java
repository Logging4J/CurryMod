package wtf.l4j.impl.modules.client;

import wtf.l4j.api.graphics.hudeditor.HudEditorScreen;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;

@ModuleInfo(name = "HudEditor", desc = "Edit Hud", category = Category.CLIENT)
public class HudEditor extends Module {

    public static OptionBoolean noGradient = new OptionBoolean("NoGradient", true);

    public HudEditor(){
        addOptions(noGradient);
    }

    @Override
    public void onEnable() {
        mc.setScreen(new HudEditorScreen());
        super.onEnable();
    }
}
