package club.l4j.currymod;

import club.l4j.currymod.event.Events;
import club.l4j.currymod.feature.FeatureManager;
import demo.knight.demobus.DemoBus;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurryMod implements ClientModInitializer {

    public static final String MOD_NAME = "CurryMod.Club";
    public static final String MOD_ID = "currymod";
    public static final String VERSION = "0.0.4";
    public static final DemoBus EVENT_BUS = new DemoBus();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static CurryMod getInstance = new CurryMod();
    public static Discord discord = new Discord();
    public static FeatureManager featureManager;

    @Override
    public void onInitializeClient() {
        featureManager = new FeatureManager();
        log("CurryMod Initializing");
        discord.start();
        EVENT_BUS.register(new Events());
    }
// hi
    public void onClose(){
        log(MOD_NAME+" is Stopping");
        discord.stop();
    }

    public void log(String message){
        LOGGER.info( "["+MOD_NAME+"] " + message);
    }
}
