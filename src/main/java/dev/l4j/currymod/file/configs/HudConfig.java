package dev.l4j.currymod.file.configs;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.hud.HudElement;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.file.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class HudConfig extends Config {


    public HudConfig() {
        super(new File(CurryMod.FOLDER, "hud"));
    }

    @Override
    public void write() {
        configFiles.getFirst().mkdirs();

        for (HudElement hudElement : CurryMod.INSTANCE.hudManager.getHudElements()) {
            writeHudElementConfig(hudElement);
        }

    }

    @Override
    public void read() {
        if (!configFiles.getFirst().exists()) {
            write();
            return;
        }

        for (HudElement hudElement : CurryMod.INSTANCE.hudManager.getHudElements()) {
            File hudConfig = new File(configFiles.getFirst(), String.format("%s.properties", hudElement.getName()));

            if (!hudConfig.exists()) {
                writeHudElementConfig(hudElement);
                continue;
            }

            Properties config = new Properties();
            try (FileInputStream input = new FileInputStream(hudConfig)) {
                config.load(input);

                boolean shown = Boolean.parseBoolean(config.getProperty("shown"));
                float scale = Float.parseFloat(config.getProperty("scale"));
                int x = Integer.parseInt(config.getProperty("x"));
                int y = Integer.parseInt(config.getProperty("y"));

                hudElement.setShown(shown);
                hudElement.scale = scale;
                hudElement.x = x;
                hudElement.y = y;

            } catch (IOException e) {
                CurryMod.LOGGER.error("Module config [{}] Failed to read", hudElement.getName());
            }
        }

    }

    public void writeHudElementConfig(HudElement hudElement) {
        File hudConfig = new File(configFiles.getFirst(), String.format("%s.properties", hudElement.getName()));
        Properties config = new Properties();

        config.setProperty("shown", String.valueOf(hudElement.isShown()));
        config.setProperty("scale", String.valueOf(hudElement.scale));
        config.setProperty("x", String.valueOf(hudElement.x));
        config.setProperty("y", String.valueOf(hudElement.y));

        try (FileOutputStream output = new FileOutputStream(hudConfig)) {
            config.store(output, String.format("Module config [%s]", hudElement.getName()));
        } catch (IOException e) {
            CurryMod.LOGGER.error("Module config [{}] Failed to write", hudElement.getName());
        }
    }
}
