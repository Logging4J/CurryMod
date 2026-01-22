package dev.logging4j.currymod.file.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.file.Config;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.Option;
import dev.logging4j.currymod.module.option.options.*;
import net.minecraft.client.util.InputUtil;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModuleConfig extends Config {

    public ModuleConfig() {
        super(
                new File(CurryMod.FOLDER, "module"),
                new File(CurryMod.FOLDER, "module/save"),
                new File(CurryMod.FOLDER, "module/load")
        );
    }

    @Override
    public void write() {
        for (File dir : configFiles) {
            dir.mkdirs();
        }

        for (Module module : CurryMod.getModuleManager().getModules()) {
            writeModuleConfig(module);
        }
    }

    @Override
    public void read() {
        if (!configFiles.getFirst().exists()) {
            write();
            return;
        }

        for (Module module : CurryMod.getModuleManager().getModules()) {
            File file = new File(configFiles.get(1), module.getName() + ".json");

            if (!file.exists()) {
                writeModuleConfig(module);
                continue;
            }

            try (FileReader reader = new FileReader(file)) {
                JsonObject json = GSON.fromJson(reader, JsonObject.class);
                if (json == null) continue;

                module.setEnabled(json.get("enabled").getAsBoolean());
                module.setShown(json.get("shown").getAsBoolean());

                JsonObject options = json.getAsJsonObject("options");
                if (options == null) continue;

                readOptions(options, module);

            } catch (Exception e) {
                CurryMod.LOGGER.error("Module config [{}] failed to read", module.getName(), e);
            }
        }
    }

    private void writeModuleConfig(Module module) {
        File file = new File(configFiles.get(1), module.getName() + ".json");

        JsonObject json = new JsonObject();
        json.addProperty("enabled", module.isEnabled());
        json.addProperty("shown", module.isShown());

        JsonObject options = new JsonObject();

        for (Option<?> option : module.getOptions()) {
            if (option instanceof OptionKeybind keybind) {
                options.addProperty(
                        option.getName(),
                        keybind.getValue().getTranslationKey()
                );
            } else if (option instanceof OptionColor color) {
                JsonArray colorValues = new JsonArray();
                colorValues.add(color.getValue().getRed());
                colorValues.add(color.getValue().getGreen());
                colorValues.add(color.getValue().getBlue());
                colorValues.add(color.getValue().getAlpha());
                options.add(option.getName(), colorValues);
            } else {
                options.add(option.getName(), GSON.toJsonTree(option.getValue()));
            }
        }

        json.add("options", options);

        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(json, writer);
        } catch (IOException e) {
            CurryMod.LOGGER.error("Module config [{}] failed to write", module.getName(), e);
        }
    }

    private void readOptions(JsonObject options, Module module) {
        for (Option<?> option : module.getOptions()) {
            JsonElement element = options.get(option.getName());
            if (element == null) continue;

            switch (option) {
                case OptionBoolean optionBoolean -> readBooleanOption(optionBoolean, element);
                case OptionKeybind optionKeybind -> readKeyBindOption(optionKeybind, element);
                case OptionMode optionMode -> readModeOption(optionMode, element);
                case OptionNumber<?> optionNumber -> readNumberOption(optionNumber, element);
                case OptionColor optionColor -> readColorOption(optionColor, element);
                default -> throw new IllegalStateException("Unexpected value: " + option);
            }
        }
    }

    private void readBooleanOption(OptionBoolean optionBoolean, JsonElement element) {
        optionBoolean.setValue(element.getAsBoolean());
    }

    private void readKeyBindOption(OptionKeybind optionKeybind, JsonElement element) {
        optionKeybind.setValue(InputUtil.fromTranslationKey(element.getAsString()));
    }

    private void readModeOption(OptionMode optionMode, JsonElement element) {
        optionMode.setValue(element.getAsString());
    }

    private void readColorOption(OptionColor optionColor, JsonElement element) {
        JsonArray rgbaValues = element.getAsJsonArray();
        Color color = new Color(rgbaValues.get(0).getAsInt(), rgbaValues.get(1).getAsInt(), rgbaValues.get(2).getAsInt(), rgbaValues.get(3).getAsInt());
        optionColor.setValue(color);
    }

    private void readNumberOption(OptionNumber<?> optionNumber, JsonElement element) {
        Number defaultValue = optionNumber.getDefaultValue();

        Object value = null;

        if (defaultValue instanceof Integer) {
            value = element.getAsInt();
        } else if (defaultValue instanceof Float) {
            value = element.getAsFloat();
        } else if (defaultValue instanceof Double) {
            value = element.getAsDouble();
        }

        if (value != null) {
            setOptionNumber(optionNumber, value);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setOptionNumber(OptionNumber option, Object value) {
        option.setValue(value);
    }
}
