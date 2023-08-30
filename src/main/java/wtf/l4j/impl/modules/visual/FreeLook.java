package wtf.l4j.impl.modules.visual;

import net.minecraft.client.option.Perspective;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;

@ModuleInfo(name = "FreeLook", desc = "F5 But Better", category = Category.VISUAL)
public class FreeLook extends Module {

    public static float cameraPitch;
    public static float cameraYaw;

    @Override
    public void onDisable() {
        mc.options.setPerspective(Perspective.FIRST_PERSON);
        super.onDisable();
    }
}
