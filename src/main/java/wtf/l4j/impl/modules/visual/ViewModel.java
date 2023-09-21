package wtf.l4j.impl.modules.visual;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionSlider;

@ModuleInfo(name = "ViewModel", desc = "Change hand's and stuff", category = Category.VISUAL)
public class ViewModel extends Module {

    public static OptionSlider mPosX = new OptionSlider("MainPosX", -4, 4, 0.1, 0);
    public static OptionSlider mPosY = new OptionSlider("MainPosY", -4, 4, 0.1, 0);
    public static OptionSlider mPosZ = new OptionSlider("MainPosZ", -4, 4, 0.1, 0);


    public static OptionSlider oPosX = new OptionSlider("OffPosX", -4, 4, 0.1, 0);
    public static OptionSlider oPosY = new OptionSlider("OffPosY", -4, 4, 0.1, 0);
    public static OptionSlider oPosZ = new OptionSlider("OffPosZ", -4, 4, 0.1, 0);


    public ViewModel(){
        addOptions(mPosX, mPosY, mPosZ, oPosX, oPosY, oPosZ);
    }

}
