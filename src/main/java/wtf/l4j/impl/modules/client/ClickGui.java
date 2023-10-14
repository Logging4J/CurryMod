package wtf.l4j.impl.modules.client;

import org.lwjgl.glfw.GLFW;
import wtf.l4j.api.graphics.clickgui.ClickGuiScreen;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;

@ModuleInfo(name = "ClickGui", desc = "Clickable user interface", category = Category.CLIENT)
public class ClickGui extends Module {

    public ClickGui(){
        setKey(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @Override
    public void onEnable() {
        mc.setScreen(new ClickGuiScreen());
        super.onDisable();
    }
}
