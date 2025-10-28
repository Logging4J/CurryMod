package dev.l4j.currymod.file;

import dev.l4j.currymod.CurryMod;

import java.io.File;
import java.util.List;

public abstract class Config {

    public List<File> configFiles;

    public Config(File... configFile) {
        this.configFiles = List.of(configFile);
    }

    public abstract void write();
    public abstract void read();
}
