package wtf.l4j;

import lombok.Getter;
import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.render.entity.EntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wtf.l4j.api.manager.*;
import wtf.l4j.api.utils.Config;

@Getter
public class CurryMod {

	private final Logger logger = LoggerFactory.getLogger("currymod");
	private final DiscordRP discordRP = new DiscordRP();
	private Managers managers;
	private static CurryMod instance;

	public void startup(){
		managers = new Managers();
		Config.load();
		Runtime.getRuntime().addShutdownHook(new Config());
	}

	public void shutdown(){
		this.logger.info("Client Shutdown");
		this.discordRP.stop();
	}

	public static CurryMod getInstance(){
		if(instance == null){
			instance = new CurryMod();
		}
		return instance;
	}
}