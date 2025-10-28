package dev.l4j.currymod.client;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.command.CommandManager;
import dev.l4j.currymod.client.hud.HudManager;
import dev.l4j.currymod.client.module.ModuleManager;
import dev.l4j.currymod.client.rotation.RotationManager;
import dev.l4j.currymod.file.ConfigManager;

public class CurryModClient {

    public CommandManager commandManager;
    public ModuleManager moduleManager;
    public HudManager hudManager;
    public ConfigManager configManager;
    public RotationManager rotationManager;

    public void onInitializeClient() {
        commandManager = new CommandManager();
        CurryMod.LOGGER.info("CommandManager Initialized");

        moduleManager = new ModuleManager();
        CurryMod.LOGGER.info("ModuleManager Initialized");

        hudManager = new HudManager();
        CurryMod.LOGGER.info("HudManager Initialized");

        configManager = new ConfigManager();
        CurryMod.LOGGER.info("ConfigManager Initialized");

        rotationManager = new RotationManager();
        CurryMod.LOGGER.info("RotationManager Initialized");

        configManager.readAll();
    }

    public void onShutdownClient() {
        configManager.saveAll();
    }
}
