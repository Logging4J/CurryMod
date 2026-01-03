package dev.logging4j.currymod.module.modules.visual;

import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.options.OptionNumber;
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

    @Override
    public String getDisplayInfo() {
        return speed.getValue().toString();
    }
}
