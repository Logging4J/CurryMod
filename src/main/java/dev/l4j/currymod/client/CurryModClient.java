package dev.l4j.currymod.client;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.cape.CapeManager;
import dev.l4j.currymod.client.command.CommandManager;
import dev.l4j.currymod.client.hud.HudManager;
import dev.l4j.currymod.client.module.ModuleManager;
import dev.l4j.currymod.file.ConfigManager;

public class CurryModClient {

    public CommandManager commandManager;
    public CapeManager capeManager;
    public ModuleManager moduleManager;
    public HudManager hudManager;
    public ConfigManager configManager;

    public void onInitializeClient() {
        commandManager = new CommandManager();
        CurryMod.LOGGER.info("CommandManager Initialized");

        capeManager = new CapeManager();
        CurryMod.LOGGER.info("CapeManager Initialized");

        moduleManager = new ModuleManager();
        CurryMod.LOGGER.info("ModuleManager Initialized");

        hudManager = new HudManager();
        CurryMod.LOGGER.info("HudManager Initialized");

        configManager = new ConfigManager();
        CurryMod.LOGGER.info("ConfigManager Initialized");

        configManager.readAll();
    }

    public void onShutdownClient() {
        configManager.saveAll();
    }
}
