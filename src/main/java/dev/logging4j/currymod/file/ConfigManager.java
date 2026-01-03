package dev.logging4j.currymod.file;

import dev.logging4j.currymod.file.impl.HudConfig;
import dev.logging4j.currymod.file.impl.ModuleConfig;
import dev.logging4j.currymod.file.impl.PrefixConfig;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private final List<Config> configs;

    public ConfigManager() {
        configs = new ArrayList<>();
        add(PrefixConfig.class);
        add(ModuleConfig.class);
        add(HudConfig.class);
    }

    @SneakyThrows
    private void add(Class<? extends Config> configClass) {
        configs.add(configClass.getConstructor().newInstance());
    }

    public void saveAll() {
        configs.forEach(Config::write);
    }

    public void readAll() {
        configs.forEach(Config::read);
    }
}
