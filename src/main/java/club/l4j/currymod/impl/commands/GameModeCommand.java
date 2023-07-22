package club.l4j.currymod.impl.commands;

import club.l4j.currymod.core.command.Command;
import net.minecraft.world.GameMode;

@Command.Construct(name = "gamemode", description = "Change your gamemode client side", alias = {"gamemode"}, usage = "gamemode <gamemode>")
public class GameModeCommand extends Command {

    @Override
    public void onTrigger(String arguments) {
        if(!arguments.isEmpty()){
            String[] split = arguments.split(" ");
            String gm = split[0].toLowerCase();
            switch (gm) {
                case "survival" -> mc.interactionManager.setGameMode(GameMode.SURVIVAL);
                case "creative" -> mc.interactionManager.setGameMode(GameMode.CREATIVE);
                case "spectator" -> mc.interactionManager.setGameMode(GameMode.SPECTATOR);
                case "adventure" -> mc.interactionManager.setGameMode(GameMode.ADVENTURE);
                default -> sendMsg("Invalid gamemode");
            }
        }else {
            sendMsg("Usage: " + getUsage());
        }
        super.onTrigger(arguments);
    }
}
