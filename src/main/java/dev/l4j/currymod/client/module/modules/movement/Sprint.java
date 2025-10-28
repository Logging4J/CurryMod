package dev.l4j.currymod.client.module.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionMode;
import dev.l4j.currymod.listener.IGameTickListener;
import dev.l4j.currymod.util.MovementUtils;

@Module.Info(name = "Sprint", description = "Auto sprint", category = Module.Category.MOVEMENT)
public class Sprint extends Module implements IGameTickListener {

    private final OptionMode mode = new OptionMode("Mode", "Rage", "Rage", "Legit");

    public Sprint() {
        addOptions(mode);
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
        if (nullCheck() || mc.player.getHungerManager().getFoodLevel() <= 6f) return;
        switch (mode.getValue()) {
            case "Rage" -> mc.player.setSprinting(true);
            case "Legit" -> {
                if (MovementUtils.isMoving() && !mc.player.isSneaking()) {
                    mc.player.setSprinting(true);
                }
            }
        }
    }

    @Override
    public String getDisplayInfo() {
        return mode.getValue();
    }
}
