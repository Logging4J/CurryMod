package dev.l4j.currymod.client.module.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.listener.IGameTickListener;
import dev.l4j.currymod.mixin.accessor.ClientPlayerEntityAccessor;

@Module.Info(name = "EntityControl", description = "Ride Entities without a saddle", category = Module.Category.MOVEMENT)
public class EntityControl extends Module implements IGameTickListener {

    private final OptionBoolean maxJump = new OptionBoolean("MaxJump", true);

    public EntityControl() {
        addOptions(maxJump);
    }

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(GameTickEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickEvent.ID, this);
    }

    @Override
    public void onGameTick() {
        if (nullCheck() || !maxJump.getValue()) return;

        ((ClientPlayerEntityAccessor) mc.player).access$mountJumpStrength(1);
    }
}
