package dev.l4j.currymod.file.configs;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.Option;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.client.module.option.options.OptionKeybind;
import dev.l4j.currymod.client.module.option.options.OptionMode;
import dev.l4j.currymod.client.module.option.options.OptionNumber;
import dev.l4j.currymod.file.Config;
import net.minecraft.client.util.InputUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

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
        for (File file : configFiles) {
            file.mkdirs();
        }

        for (Module module : CurryMod.INSTANCE.moduleManager.getModules()) {
            writeModuleConfig(module);
        }
    }

    @Override
    public void read() {
        if (!configFiles.getFirst().exists()) {
            write();
            return;
        }

        for (Module module : CurryMod.INSTANCE.moduleManager.getModules()) {
            File moduleConfig = new File(configFiles.get(1), String.format("%s.properties", module.getName()));

            if (!moduleConfig.exists()) {
                writeModuleConfig(module);
                continue;
            }

            Properties config = new Properties();
            try (FileInputStream input = new FileInputStream(moduleConfig)) {
                config.load(input);

                boolean enabled = Boolean.parseBoolean(config.getProperty("enabled"));
                boolean shown = Boolean.parseBoolean(config.getProperty("shown"));

                module.setEnabled(enabled);
                module.setShown(shown);

                for (Option<?> option : module.getOptions()) {
                    String propertyValue = config.getProperty(option.getName());
                    if (propertyValue == null) continue;

                    if (option instanceof OptionBoolean optionBoolean) {
                        optionBoolean.setValue(Boolean.parseBoolean(propertyValue));

                    } else if (option instanceof OptionKeybind optionKeybind) {
                        optionKeybind.setValue(InputUtil.fromTranslationKey(config.getProperty("Key")));

                    } else if (option instanceof OptionMode optionMode) {
                        optionMode.setValue(propertyValue);

                    } else if (option instanceof OptionNumber<?> optionNumber) {
                        try {
                            Number parsedNumber = null;
                            Number defaultValue = optionNumber.getDefaultValue();

                            if (defaultValue instanceof Integer) {
                                parsedNumber = Integer.parseInt(propertyValue);
                            } else if (defaultValue instanceof Float) {
                                parsedNumber = Float.parseFloat(propertyValue);
                            } else if (defaultValue instanceof Double) {
                                parsedNumber = Double.parseDouble(propertyValue);
                            }

                            if (parsedNumber != null) {
                                @SuppressWarnings("unchecked")
                                OptionNumber<Number> castedOption = (OptionNumber<Number>) optionNumber;
                                castedOption.setValue(parsedNumber);
                            }

                        } catch (NumberFormatException e) {
                            System.err.println("Invalid number format for option: " + option.getName());
                        }
                    }
                }
            } catch (IOException e) {
                CurryMod.LOGGER.error("Module config [{}] Failed to read", module.getName());
            }
        }
    }

    public void writeModuleConfig(Module module) {
        File moduleConfig = new File(configFiles.get(1), String.format("%s.properties", module.getName()));
        Properties config = new Properties();

        config.setProperty("enabled", String.valueOf(module.isEnabled()));
        config.setProperty("shown", String.valueOf(module.isShown()));

        module.getOptions().forEach(option -> {
            config.setProperty(option.getName(), option instanceof OptionKeybind optionKeybind ? optionKeybind.getValue().getTranslationKey() : String.valueOf(option.getValue()));
        });

        try (FileOutputStream output = new FileOutputStream(moduleConfig)) {
            config.store(output, String.format("Module config [%s]", module.getName()));
        } catch (IOException e) {
            CurryMod.LOGGER.error("Module config [{}] Failed to write", module.getName());
        }
    }
}
