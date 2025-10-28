package dev.l4j.currymod.client.module.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.listener.IGameTickListener;

@Module.Info(name = "AutoRespawn", description = "Automatically respawn.", category = Module.Category.MISC)
public class AutoRespawn extends Module implements IGameTickListener {

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
        if (nullCheck()) return;

        if (mc.player.isDead()) {
            mc.player.requestRespawn();
        }
    }
}
