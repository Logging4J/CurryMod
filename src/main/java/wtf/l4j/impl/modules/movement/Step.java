package wtf.l4j.impl.modules.movement;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "Step", desc = "Step up blocks like stairs", category = Category.MOVEMENT)
public class Step extends Module {
    public OptionSlider height = new OptionSlider("Height", 0.5, 3, 0.5,1);

    public Step(){
        addOptions(height);
    }

    @Override
    public void onEnable() {
        mc.player.setStepHeight(height.getFloatValue());
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.player.setStepHeight(0.5f);
        super.onDisable();
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY + "[" + TextUtil.WHITE + height.getFloatValue() + TextUtil.GRAY + "]";
    }
}
