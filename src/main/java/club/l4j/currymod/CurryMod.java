package club.l4j.currymod;

import club.l4j.currymod.core.event.Events;
import club.l4j.currymod.core.manager.CommandManager;
import club.l4j.currymod.core.manager.HackManager;
import club.l4j.currymod.core.manager.HudManager;
import club.l4j.currymod.core.util.Config;
import club.l4j.currymod.core.manager.ColorManager;
import demo.knight.demobus.DemoBus;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurryMod implements ClientModInitializer {

    public static final String MOD_NAME = "CurryMod.Club";
    public static final String MOD_ID = "currymod";
    public static final String VERSION = "0.0.8";
    public static final DemoBus EVENT_BUS = new DemoBus();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static CurryMod getInstance = new CurryMod();
    public static Discord discord = new Discord();
    public static HackManager hackManager;
    public static CommandManager commandManager;
    public static HudManager hudManager;
    public static ColorManager colorManager;
    @Override
    public void onInitializeClient() {
        log("CurryMod Initializing");
        hackManager = new HackManager();
        commandManager = new CommandManager();
        hudManager = new HudManager();
        colorManager = new ColorManager();
        discord.start();
        Runtime.getRuntime().addShutdownHook(new Config());
        Config.load();
        EVENT_BUS.register(new Events());
    }

    public void onClose(){
        log(MOD_NAME+" is Stopping");
        Config.save();
        discord.stop();
    }

    public void log(String message){
        LOGGER.info( "["+MOD_NAME+"] " + message);
    }

}
