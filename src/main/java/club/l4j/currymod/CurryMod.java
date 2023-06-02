package club.l4j.currymod;

import club.l4j.currymod.event.Events;
import club.l4j.currymod.feature.FeatureManager;
import demo.knight.demobus.DemoBus;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurryMod implements ClientModInitializer {

    public static final String MOD_NAME = "CurryMod.Club";
    public static final String MOD_ID = "currymod";
    public static final String VERSION = "0.0.3";
    public static final DemoBus EVENT_BUS = new DemoBus();
    public static final LaunchType LAUNCH_TYPE = LaunchType.DEVELOPER;
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static CurryMod getInstance = new CurryMod();
    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static FeatureManager featureManager;

    @Override
    public void onInitializeClient() {
        log("CurryMod Initializing");
        EVENT_BUS.register(new Events());
        featureManager = new FeatureManager();
    }

    public void onClose(){
        log(MOD_NAME+" is Stopping");
    }

    public void log(String message){
        LOGGER.info( "["+MOD_NAME+"] " + message);
    }

    public enum LaunchType{
        DEVELOPER,
        TUCO,
        USER
    }
}
