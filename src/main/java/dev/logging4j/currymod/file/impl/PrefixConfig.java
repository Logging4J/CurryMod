package dev.logging4j.currymod.file.impl;

import com.google.gson.JsonObject;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.file.Config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PrefixConfig extends Config {

    public PrefixConfig() {
        super(new File(CurryMod.FOLDER, "prefix.json"));
    }

    @Override
    public void write() {
        JsonObject json = new JsonObject();
        json.addProperty("prefix", CurryMod.getCommandManager().getPrefix());

        try (FileWriter writer = new FileWriter(configFiles.getFirst())) {
            GSON.toJson(json, writer);
        } catch (IOException e) {
            CurryMod.LOGGER.error("Prefix config failed to write", e);
        }
    }

    @Override
    public void read() {
        File file = configFiles.getFirst();

        if (!file.exists()) {
            write();
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);

            if (json != null && json.has("prefix")) {
                CurryMod.getCommandManager().setPrefix(
                        json.get("prefix").getAsString()
                );
            }
        } catch (IOException e) {
            CurryMod.LOGGER.error("Prefix config failed to read", e);
        }
    }
}
