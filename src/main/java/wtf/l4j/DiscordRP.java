package wtf.l4j;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import wtf.l4j.api.utils.MinecraftInterface;

public class DiscordRP implements MinecraftInterface {

    public DiscordRichPresence discordRichPresence = new DiscordRichPresence();

    public void start(){
        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        String discordID = "1119077388962242600";
        DiscordRPC.INSTANCE.Discord_Initialize(discordID, eventHandlers, true, null);
        discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        discordRichPresence.details = "Straight out of Mumbai India";
        discordRichPresence.largeImageKey = "edpcurrymodlarge";
        discordRichPresence.smallImageKey = "edpcurrymodsmall";
        discordRichPresence.largeImageText = "slappin nn's" + (!mc.isInSingleplayer() ? "on " + mc.player.getServer().getServerIp() : "in singleplayer");
        discordRichPresence.state = null;
        DiscordRPC.INSTANCE.Discord_UpdatePresence(discordRichPresence);
    }

    public void stop(){
        DiscordRPC.INSTANCE.Discord_Shutdown();
        DiscordRPC.INSTANCE.Discord_ClearPresence();
    }
}
