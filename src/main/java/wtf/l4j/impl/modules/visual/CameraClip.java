package wtf.l4j.impl.modules.visual;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "CameraClip", desc = "clip camera through blocks", category = Category.VISUAL)
public class CameraClip extends Module {

    public static OptionSlider distance = new OptionSlider("Distance", 1, 10, 1, 5);

    public CameraClip(){
        addOptions(distance);
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE + distance.getIntValue() + TextUtil.GRAY+"]";
    }

}
