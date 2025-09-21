package dev.l4j.currymod.client.module.modules.visual;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionNumber;
import lombok.Getter;

@Module.Info(name = "SwingSpeed", description = "Change swing speed.", category = Module.Category.VISUAL)
public class SwingSpeed extends Module {

    @Getter
    private final OptionNumber<Integer> speed = new OptionNumber<>("Speed", 6, 1, 20, 1);

    public SwingSpeed() {
        addOptions(speed);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
