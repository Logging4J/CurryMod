package dev.l4j.currymod.client.module.modules.visual;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.client.module.option.options.OptionNumber;
import lombok.Getter;

@Getter
@Module.Info(name = "ExtraTab", description = "Bigger Tab", category = Module.Category.VISUAL)
public class ExtraTab extends Module {

    private final OptionNumber<Integer> size = new OptionNumber<>("Size", 100, 1, 1000, 1);
    private final OptionNumber<Integer> height = new OptionNumber<>("Height", 20, 1, 1000, 1);
    private final OptionBoolean highlightSelf = new OptionBoolean("Highlight Self", true);

    public ExtraTab() {
        addOptions(size, height, highlightSelf);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
