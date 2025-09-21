package dev.l4j.currymod.client.module.modules.client;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.screen.hud.HudEditorScreen;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Module.Info(name = "HudEditor", description = "Hud Editor", category = Module.Category.CLIENT)
public class HudEditor extends Module {

    @Override
    protected void onEnable() {
        if (nullCheck()) return;
        mc.setScreen(HudEditorScreen.getInstance());
    }

    @Override
    protected void onDisable() {

    }
}
