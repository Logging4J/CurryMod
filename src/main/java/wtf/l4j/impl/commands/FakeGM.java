package wtf.l4j.impl.commands;

import net.minecraft.world.GameMode;
import wtf.l4j.api.command.Command;
import wtf.l4j.api.command.CommandInfo;

@CommandInfo(name = "FakeGM", desc = "Changes game mode client side", alais = "fakegm")
public class FakeGM extends Command {

    @Override
    public void execute(String arguments) {
        if(!arguments.isEmpty()){
            String[] split = arguments.split(" ");
            String gm = split[0].toLowerCase();
            switch (gm) {
                case "survival" -> mc.interactionManager.setGameMode(GameMode.SURVIVAL);
                case "creative" -> mc.interactionManager.setGameMode(GameMode.CREATIVE);
                case "spectator" -> mc.interactionManager.setGameMode(GameMode.SPECTATOR);
                case "adventure" -> mc.interactionManager.setGameMode(GameMode.ADVENTURE);
                default -> errorMessage("Invalid game mode");
            }
        }else {
            errorMessage("Choose a game mode(survival, creative and etc)");
        }
    }
}
