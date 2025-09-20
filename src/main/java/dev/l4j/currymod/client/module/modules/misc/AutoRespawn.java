package dev.l4j.currymod.client.module.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.listener.ITickListener;

@Module.Info(name = "AutoRespawn", description = "Automatically respawn.", category = Module.Category.MISC)
public class AutoRespawn extends Module implements ITickListener {

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(TickEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(TickEvent.ID, this);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        if (mc.player.isDead()) {
            mc.player.requestRespawn();
        }
    }
}
