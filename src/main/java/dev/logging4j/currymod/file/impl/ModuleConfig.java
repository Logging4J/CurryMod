package dev.logging4j.currymod.file.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.file.Config;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.Option;
import dev.logging4j.currymod.module.option.options.OptionBoolean;
import dev.logging4j.currymod.module.option.options.OptionKeybind;
import dev.logging4j.currymod.module.option.options.OptionMode;
import dev.logging4j.currymod.module.option.options.OptionNumber;
import net.minecraft.client.util.InputUtil;

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

                for (Option<?> option : module.getOptions()) {
                    JsonElement element = options.get(option.getName());
                    if (element == null) continue;

                    if (option instanceof OptionBoolean optionBoolean) {
                        optionBoolean.setValue(element.getAsBoolean());

                    } else if (option instanceof OptionKeybind optionKeybind) {
                        optionKeybind.setValue(
                                InputUtil.fromTranslationKey(element.getAsString())
                        );

                    } else if (option instanceof OptionMode optionMode) {
                        optionMode.setValue(element.getAsString());

                    } else if (option instanceof OptionNumber<?> optionNumber) {
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
                }

            } catch (Exception e) {
                CurryMod.LOGGER.error("Module config [{}] failed to read", module.getName(), e);
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void setOptionNumber(OptionNumber option, Object value) {
        option.setValue(value);
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
}
