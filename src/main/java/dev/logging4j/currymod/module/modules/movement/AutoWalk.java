package dev.logging4j.currymod.module.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.listener.IPrePlayerTickListener;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.options.OptionBoolean;
import net.minecraft.client.option.KeyBinding;

@Module.Info(name = "AutoWalk", description = "Auto walk", category = Module.Category.MOVEMENT)
public class AutoWalk extends Module implements IPrePlayerTickListener {

    private final OptionBoolean jump = new OptionBoolean("Jump", true);

    public AutoWalk() {
        addOptions(jump);
    }

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(PrePlayerTickEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(PrePlayerTickEvent.ID, this);

        if (nullCheck()) return;

        KeyBinding.setKeyPressed(mc.options.forwardKey.getDefaultKey(), false);
        KeyBinding.setKeyPressed(mc.options.jumpKey.getDefaultKey(), false);
    }

    @Override
    public void onPrePlayerTick() {
        KeyBinding.setKeyPressed(mc.options.forwardKey.getDefaultKey(), true);
        if (jump.getValue()) {
            KeyBinding.setKeyPressed(mc.options.jumpKey.getDefaultKey(), true);
        }
    }
}
