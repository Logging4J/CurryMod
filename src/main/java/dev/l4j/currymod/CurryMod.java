package dev.l4j.currymod;

import dev.l4j.currymod.client.CurryModClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CurryMod implements ClientModInitializer {

    public static final String MOD_ID = "currymod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final File FOLDER = FabricLoader.getInstance().getGameDir().resolve(MOD_ID).toFile();

    public static CurryModClient INSTANCE;

    @Override
    public void onInitializeClient() {
        if (!FOLDER.exists()) {
            FOLDER.getParentFile().mkdirs();
            FOLDER.mkdir();
        }

        INSTANCE = new CurryModClient();
        INSTANCE.onInitializeClient();
    }
}
