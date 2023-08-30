package wtf.l4j.impl.commands;

import wtf.l4j.api.command.Command;
import wtf.l4j.api.command.CommandInfo;
import wtf.l4j.api.utils.PlayerUtils;

@CommandInfo(name = "UUID", desc = "Get uuid from a player", alais = "uuid")
public class UUID extends Command {

    @Override
    public void execute(String arguments) {
        if(!arguments.isEmpty()) {
            String[] split = arguments.split(" ");
            String name = split[0];
            String playerUUID = PlayerUtils.getUUIDFromName(name);
            if(playerUUID != null) {
                basicMessage(name + " has the UUID of " + playerUUID);
            }else {
                basicMessage("Player " + name + " does not exist");
            }
        }else {
            errorMessage("Choose a players name");
        }
    }
}
