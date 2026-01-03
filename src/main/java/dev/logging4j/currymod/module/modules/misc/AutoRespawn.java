package dev.logging4j.currymod.module.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.listener.IPrePlayerTickListener;
import dev.logging4j.currymod.module.Module;

@Module.Info(name = "AutoRespawn", description = "Automatically respawn.", category = Module.Category.MISC)
public class AutoRespawn extends Module implements IPrePlayerTickListener {

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
        if (mc.player.isDead()) {
            mc.player.requestRespawn();
        }
    }
}
