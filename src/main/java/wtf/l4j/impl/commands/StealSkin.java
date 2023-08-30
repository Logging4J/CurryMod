package wtf.l4j.impl.commands;

import wtf.l4j.api.command.Command;
import wtf.l4j.api.command.CommandInfo;
import wtf.l4j.api.utils.Config;
import wtf.l4j.api.utils.PlayerUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@CommandInfo(name = "StealSkin", desc = "Steals peoples skin", alais = "stealskin")
public class StealSkin extends Command {

    @Override
    public void execute(String arguments)  {
        if(!arguments.isEmpty()) {
            String[] split = arguments.split(" ");
            String name = split[0].toLowerCase();
            String uuid = PlayerUtils.getUUIDFromName(name);
            (new Thread(() -> {
                try (InputStream in = new URL("https://skinmc.net/api/v1/skins/uuid/" + uuid).openStream()) {
                    Files.copy(in, Paths.get(Config.getSkinPath() + "\\" + uuid + ".jpg"));
                    basicMessage("Image Saved as " + uuid + ".jpg");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            })).start();
        }else {
            basicMessage("Provide a player name");
        }
    }
}
