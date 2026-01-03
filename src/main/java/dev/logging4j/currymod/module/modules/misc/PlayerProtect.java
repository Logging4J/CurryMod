package dev.logging4j.currymod.module.modules.misc;

import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.options.OptionBoolean;
import lombok.Getter;

@Getter
@Module.Info(name = "PlayerProtect", description = "Hide player name or skin clientsided", category = Module.Category.MISC)
public class PlayerProtect extends Module {

    private final OptionBoolean hideName = new OptionBoolean("Name", true);
    private final OptionBoolean hideSkin = new OptionBoolean("Skin", true);

    public PlayerProtect() {
        addOptions(hideSkin, hideName);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
