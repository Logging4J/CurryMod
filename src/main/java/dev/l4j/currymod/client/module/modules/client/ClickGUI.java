package dev.l4j.currymod.client.module.modules.client;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.screen.clickgui.ClickGUIScreen;

@Module.Info(name = "ClickGUI", description = "clickgui", category = Module.Category.CLIENT)
public class ClickGUI extends Module {

    @Override
    protected void onEnable() {
        mc.setScreen(ClickGUIScreen.getInstance());
    }

    @Override
    protected void onDisable() {

    }
}
