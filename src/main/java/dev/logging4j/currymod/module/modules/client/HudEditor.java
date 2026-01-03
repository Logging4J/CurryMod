package dev.logging4j.currymod.module.modules.client;

import dev.logging4j.currymod.module.option.options.OptionBoolean;
import dev.logging4j.currymod.screen.hud.HudEditorScreen;
import lombok.Getter;
import dev.logging4j.currymod.module.Module;

@Getter
@Module.Info(name = "HudEditor", description = "Hud Editor", category = Module.Category.CLIENT)
public class HudEditor extends Module {

    private final OptionBoolean descriptionNigger = new OptionBoolean("Description Nigger", true);

    public HudEditor() {
        addOptions(descriptionNigger);
    }

    @Override
    protected void onEnable() {
        if (nullCheck()) return;
        mc.setScreen(HudEditorScreen.getInstance());
    }

    @Override
    protected void onDisable() {

    }
}
