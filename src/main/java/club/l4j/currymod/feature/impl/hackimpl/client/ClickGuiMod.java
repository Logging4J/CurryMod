package club.l4j.currymod.feature.impl.hackimpl.client;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.graphics.clickgui.ClickGuiScreen;
import org.lwjgl.glfw.GLFW;

@Hack.Construct(name = "ClickGUI", description = "clickable GUI", category = Hack.Category.CLIENT)
public class ClickGuiMod extends Hack {

    public ClickGuiMod(){
        setKey(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @Override
    public void onEnable() {
        mc.setScreen(new ClickGuiScreen());
        super.onEnable();
    }
}
