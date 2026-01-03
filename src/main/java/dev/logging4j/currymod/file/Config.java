package dev.logging4j.currymod.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.List;

public abstract class Config {

    protected static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public List<File> configFiles;

    public Config(File... configFile) {
        this.configFiles = List.of(configFile);
    }

    public abstract void write();
    public abstract void read();
}
