package dev.logging4j.currymod.module.modules.client;

import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.options.OptionColor;

import java.awt.*;

@Module.Info(name = "Colors", description = "Client Colors", category = Module.Category.CLIENT)
public class Colors extends Module {

    private final OptionColor clientColor = new OptionColor("ClientColor", Color.GREEN);

    public Colors() {
        addOptions(clientColor);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
