package wtf.l4j;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.gui.hud.PlayerListHud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wtf.l4j.api.event.Event;
import wtf.l4j.api.manager.*;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.utils.Config;

public class CurryMod implements ClientModInitializer {

	public static final CurryMod INSTANCE = new CurryMod();
	public static final String VERSION = "0.0.8";
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

	public void postEvent(Event e){
		for(Module m : Managers.getModuleManager().getModules()){
			if(m.isEnabled()){
				m.onEvent(e);
			}
		}
	}
}