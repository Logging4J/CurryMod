package dev.logging4j.currymod.module.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.listener.IPrePlayerTickListener;
import dev.logging4j.currymod.mixin.accessor.ClientPlayerEntityAccessor;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.options.OptionBoolean;

@Module.Info(name = "EntityControl", description = "Ride Entities without a saddle", category = Module.Category.MOVEMENT)
public class EntityControl extends Module implements IPrePlayerTickListener {

    private final OptionBoolean maxJump = new OptionBoolean("MaxJump", true);

    public EntityControl() {
        addOptions(maxJump);
    }

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(PrePlayerTickEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(PrePlayerTickEvent.ID, this);
    }

    @Override
    public void onPrePlayerTick() {
        if (nullCheck() || !maxJump.getValue()) return;

        ((ClientPlayerEntityAccessor) mc.player).access$mountJumpStrength(1);
    }
}
