package dev.logging4j.currymod.module.modules.client;

import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.screen.clickgui.ClickGUIScreen;

@Module.Info(name = "ClickGUI", description = "GUI", category = Module.Category.CLIENT)
public class ClickGUI extends Module {

    @Override
    protected void onEnable() {
        if (nullCheck()) return;

        mc.setScreen(ClickGUIScreen.getInstance());
    }

    @Override
    protected void onDisable() {

    }
}
