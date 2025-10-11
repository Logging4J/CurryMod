package dev.l4j.currymod.client.module.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.listener.ITickListener;
import net.minecraft.client.option.KeyBinding;

@Module.Info(name = "AutoWalk", description = "Auto walk", category = Module.Category.MOVEMENT)
public class AutoWalk extends Module implements ITickListener {

    private final OptionBoolean jump = new OptionBoolean("Jump", true);

    public AutoWalk() {
        addOptions(jump);
    }

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(TickEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(TickEvent.ID, this);

        if (nullCheck()) return;

        KeyBinding.setKeyPressed(mc.options.forwardKey.getDefaultKey(), false);
        KeyBinding.setKeyPressed(mc.options.jumpKey.getDefaultKey(), false);
    }

    @Override
    public void onTick() {
        KeyBinding.setKeyPressed(mc.options.forwardKey.getDefaultKey(), true);
        if (jump.getValue()) {
            KeyBinding.setKeyPressed(mc.options.jumpKey.getDefaultKey(), true);
        }
    }
}
