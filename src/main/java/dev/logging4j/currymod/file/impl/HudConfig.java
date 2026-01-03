package dev.logging4j.currymod.file.impl;

import com.google.gson.JsonObject;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.file.Config;
import dev.logging4j.currymod.hud.HudElement;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HudConfig extends Config {

    public HudConfig() {
        super(new File(CurryMod.FOLDER, "hud"));
    }

    @Override
    public void write() {
        configFiles.getFirst().mkdirs();

        for (HudElement hudElement : CurryMod.getHudManager().getHudElements()) {
            writeHudElementConfig(hudElement);
        }
    }

    @Override
    public void read() {
        File dir = configFiles.getFirst();

        if (!dir.exists()) {
            write();
            return;
        }

        for (HudElement hudElement : CurryMod.getHudManager().getHudElements()) {
            File file = new File(dir, hudElement.getName() + ".json");

            if (!file.exists()) {
                writeHudElementConfig(hudElement);
                continue;
            }

            try (FileReader reader = new FileReader(file)) {
                JsonObject json = GSON.fromJson(reader, JsonObject.class);
                if (json == null) continue;

                if (json.has("shown"))
                    hudElement.setShown(json.get("shown").getAsBoolean());

                if (json.has("scale"))
                    hudElement.scale = json.get("scale").getAsFloat();

                if (json.has("x"))
                    hudElement.x = json.get("x").getAsInt();

                if (json.has("y"))
                    hudElement.y = json.get("y").getAsInt();

            } catch (Exception e) {
                CurryMod.LOGGER.error(
                        "HUD config [{}] failed to read",
                        hudElement.getName(),
                        e
                );
            }
        }
    }

    private void writeHudElementConfig(HudElement hudElement) {
        File file = new File(
                configFiles.getFirst(),
                hudElement.getName() + ".json"
        );

        JsonObject json = new JsonObject();
        json.addProperty("shown", hudElement.isShown());
        json.addProperty("scale", hudElement.scale);
        json.addProperty("x", hudElement.x);
        json.addProperty("y", hudElement.y);

        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(json, writer);
        } catch (IOException e) {
            CurryMod.LOGGER.error(
                    "HUD config [{}] failed to write",
                    hudElement.getName(),
                    e
            );
        }
    }
}
