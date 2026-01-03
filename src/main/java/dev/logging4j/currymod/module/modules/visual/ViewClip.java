package dev.logging4j.currymod.module.modules.visual;

import dev.logging4j.currymod.module.option.options.OptionNumber;
import lombok.Getter;
import dev.logging4j.currymod.module.Module;

@Getter
@Module.Info(name = "ViewClip", description = "Camera can now clip through blocks in F5", category = Module.Category.VISUAL)
public class ViewClip extends Module {

    private final OptionNumber<Integer> distance = new OptionNumber<>("Distance", 7, 0, 20, 1);

    public ViewClip() {
        addOptions(distance);
    }

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public String getDisplayInfo() {
        return distance.getValue().toString();
    }
}
