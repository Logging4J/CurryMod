package dev.logging4j.currymod;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.command.CommandManager;
import dev.logging4j.currymod.file.ConfigManager;
import dev.logging4j.currymod.hud.HudManager;
import dev.logging4j.currymod.listener.IGameJoinListener;
import dev.logging4j.currymod.module.ModuleManager;
import dev.logging4j.currymod.util.ChatUtils;
import lombok.Getter;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CurryMod implements ClientModInitializer, IGameJoinListener {

    public static final String MOD_ID = "currymod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final File FOLDER = FabricLoader.getInstance().getGameDir().resolve(MOD_ID).toFile();

    @Getter
    private static ModuleManager moduleManager;

    @Getter
    private static CommandManager commandManager;

    @Getter
    private static HudManager hudManager;

    private static ConfigManager configManager;

    private boolean firstLaunch = false;

    @Override
    public void onInitializeClient() {
        DietrichEvents2.global().subscribe(GameJoinEvent.ID, this);

        if (!FOLDER.exists()) {
            firstLaunch = true;
            FOLDER.getParentFile().mkdirs();
            FOLDER.mkdir();
        }

        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
        hudManager = new HudManager();
        configManager = new ConfigManager();

        configManager.readAll();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DietrichEvents2.global().unsubscribe(GameJoinEvent.ID, this);
            configManager.saveAll();
        }));
    }

    @Override
    public void onGameJoin(GameJoinEvent event) {
        if (!firstLaunch) return;

        String welcomeMsg = "Welcome to CurryMod " + ChatUtils.convertTextRainbow("Chudington") + ",  Default binds are  " + Formatting.WHITE + "[" + Formatting.GREEN + "ClickGUI"+ Formatting.GRAY + ": RShift, " + Formatting.GREEN + "Prefix" + Formatting.GRAY +": -" + Formatting.WHITE + "]";

        ChatUtils.sendClientMessage(welcomeMsg);

        firstLaunch = false;
    }
}
