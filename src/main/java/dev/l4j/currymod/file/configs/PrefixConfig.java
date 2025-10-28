package dev.l4j.currymod.file.configs;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.file.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PrefixConfig extends Config {

    public PrefixConfig() {
        super(new File(CurryMod.FOLDER, "prefix.properties"));
    }

    @Override
    public void write() {
        Properties config = new Properties();
        String prefix = CurryMod.INSTANCE.commandManager.getPrefix();

        config.setProperty("prefix", prefix);

        try (FileOutputStream output = new FileOutputStream(configFiles.getFirst())) {
            config.store(output, "Prefix config");
        } catch (IOException e) {
            CurryMod.LOGGER.error("Prefix config Failed to write");
        }
    }

    @Override
    public void read() {
        if (!configFiles.getFirst().exists()) {
            write();
            return;
        }

        Properties config = new Properties();
        try (FileInputStream input = new FileInputStream(configFiles.getFirst())) {
            config.load(input);

            String prefix = config.getProperty("prefix");
            CurryMod.INSTANCE.commandManager.setPrefix(prefix);

        } catch (IOException e) {
            CurryMod.LOGGER.error("Prefix config Failed to read");
            e.printStackTrace();
        }

    }
}
