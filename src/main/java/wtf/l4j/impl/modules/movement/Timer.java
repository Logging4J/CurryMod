package wtf.l4j.impl.modules.movement;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "Timer", desc = "Outdated Speed Hack", category = Category.MOVEMENT)
public class Timer extends Module {

    public static OptionSlider speed = new OptionSlider("Speed",0.1f,20f,0.1f,5f);

    public Timer(){
        addOptions(speed);
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY + "[" + TextUtil.WHITE + speed.getFloatValue() + TextUtil.GRAY +"]";
    }
}
