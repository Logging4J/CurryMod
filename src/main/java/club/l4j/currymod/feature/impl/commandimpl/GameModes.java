package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import net.minecraft.world.GameMode;

import java.util.Locale;

@Command.Construct(name = "gamemode", description = "Change your gamemode client side", alias = {"gamemode", "gm"})
public class GameModes extends Command {

    @Override
    public void onTrigger(String arguments) {
        if(arguments.isEmpty()){
            String[] split = arguments.split(" ");
            String gm = split[0].toLowerCase();
            switch (gm) {
                case "survival":
                    mc.interactionManager.setGameMode(GameMode.SURVIVAL);
                    sendMsg("Set client-side gamemode to survival");
                    break;
                case "creative":
                    mc.interactionManager.setGameMode(GameMode.CREATIVE);
                    sendMsg("Set client-side gamemode to creative");
                    break;
                case "spectator":
                    mc.interactionManager.setGameMode(GameMode.SPECTATOR);
                    sendMsg("Set client-side gamemode to spectator");
                    break;
                case "adventure":
                    mc.interactionManager.setGameMode(GameMode.ADVENTURE);
                    sendMsg("Set client-side gamemode to adventure");
                    break;
                default:
                    sendMsg("Invalid gamemode");
            }
        }else {
            sendMsg("Please specify a gamemode");
        }
        super.onTrigger(arguments);
    }
}
