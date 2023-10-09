package wtf.l4j;

import lombok.Getter;
import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.render.entity.EntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wtf.l4j.api.manager.*;
import wtf.l4j.api.utils.Config;

public class CurryMod {

	@Getter private final Logger logger = LoggerFactory.getLogger("currymod");
	@Getter private Managers managers;
	private static CurryMod instance;

	private final DiscordRP discordRP = new DiscordRP();


	public void startup(){
		this.discordRP.start();
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