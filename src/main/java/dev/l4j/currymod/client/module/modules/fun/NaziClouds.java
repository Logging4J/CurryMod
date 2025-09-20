package dev.l4j.currymod.client.module.modules.fun;

import dev.l4j.currymod.client.module.Module;

@Module.Info(name = "NaziClouds", description = "changes the clouds to swastikas", category = Module.Category.FUN)
public class NaziClouds extends Module {

    @Override
    protected void onEnable() {
        if (nullCheck()) return;
        mc.reloadResources();
    }

    @Override
    protected void onDisable() {
        if (nullCheck()) return;
        mc.reloadResources();
    }
}
