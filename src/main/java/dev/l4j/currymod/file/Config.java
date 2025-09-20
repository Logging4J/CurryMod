package dev.l4j.currymod.file;

import dev.l4j.currymod.CurryMod;

import java.io.File;

public abstract class Config {
    public static final File PREFIX_CONFIG_FILE = new File(CurryMod.FOLDER, "prefix.properties");
    public static final File MODULE_CONFIG_FOLDER = new File(CurryMod.FOLDER, "module");
    public static final File HUD_CONFIG_FOLDER = new File(CurryMod.FOLDER, "hud");
    public static final File MODULE_CONFIG_SAVE_FOLDER = new File(MODULE_CONFIG_FOLDER, "save");
    public static final File MODULE_CONFIG_LOAD_FOLDER = new File(MODULE_CONFIG_FOLDER, "load");

    public abstract void write();
    public abstract void read();
}
