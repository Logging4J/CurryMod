package wtf.l4j;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.render.entity.EntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wtf.l4j.api.manager.*;
import wtf.l4j.api.utils.Config;

public class CurryMod implements ClientModInitializer {

	public static final CurryMod INSTANCE = new CurryMod();
	public static final String VERSION = "0.0.9";
	public static final Logger LOGGER = LoggerFactory.getLogger("currymod");

	public DiscordRP discordRP = new DiscordRP();

	@Override
	public void onInitializeClient() {
		discordRP.start();
		Runtime.getRuntime().addShutdownHook(new Config());
		Managers.init();
		Config.load();
	}

	public void shutdown(){
		LOGGER.info("Client Shutdown");
		discordRP.stop();
	}
}