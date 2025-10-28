package dev.l4j.currymod.client.module.modules.client;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.client.screen.clickgui.ClickGUIScreen;
import lombok.Getter;

@Getter
@Module.Info(name = "ClickGUI", description = "clickgui", category = Module.Category.CLIENT)
public class ClickGUI extends Module {

    private final OptionBoolean descriptionNigger = new OptionBoolean("Description Nigger", true);

    public ClickGUI() {
        addOptions(descriptionNigger);
    }

    @Override
    protected void onEnable() {
        if (nullCheck()) return;
        mc.setScreen(ClickGUIScreen.getInstance());
    }

    @Override
    protected void onDisable() {
        if (nullCheck()) return;
        mc.setScreen(null);
    }
}
