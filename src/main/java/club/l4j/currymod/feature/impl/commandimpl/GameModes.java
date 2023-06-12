package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import net.minecraft.world.GameMode;

@Command.Construct(name = "gamemode", description = "Change your gamemode client side", alias = {"gamemode"})
public class GameModes extends Command {

    @Override
    public void onTrigger(String arguments) {
        if(arguments.length() != 0){
            String[] split = arguments.split(" ");
            String gm = split[0];
            if(gm.equals("survival")){
                mc.interactionManager.setGameMode(GameMode.SURVIVAL);
                sendMsg("Set client side gamemode to survival");
            } else if (gm.equals("creative")){
                mc.interactionManager.setGameMode(GameMode.CREATIVE);
                sendMsg("Set client side gamemode to creative");
            } else {
                sendMsg("Invalid gamemode");
            }
        }else {
            sendMsg("Please specify a gamemode");
        }
        super.onTrigger(arguments);
    }
}
