package dev.l4j.currymod.client.module.modules.misc;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
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
