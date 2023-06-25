package club.l4j.currymod;

import club.l4j.currymod.event.Events;
import club.l4j.currymod.feature.FeatureManager;
import club.l4j.currymod.util.Config;
import club.l4j.currymod.util.UniColor;
import demo.knight.demobus.DemoBus;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurryMod implements ClientModInitializer {

    public static final String MOD_NAME = "CurryMod.Club";
    public static final String MOD_ID = "currymod";
    public static final String VERSION = "0.0.6";
    public static final DemoBus EVENT_BUS = new DemoBus();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static CurryMod getInstance = new CurryMod();
    public static Discord discord = new Discord();
    public static FeatureManager featureManager;
    public static UniColor uniColor;

    @Override
    public void onInitializeClient() {
        log("CurryMod Initializing");
        featureManager = new FeatureManager();
        uniColor = new UniColor();
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
