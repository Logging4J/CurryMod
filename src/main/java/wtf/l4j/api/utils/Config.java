package wtf.l4j.api.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import lombok.Getter;
import wtf.l4j.CurryMod;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.option.Option;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.module.option.options.OptionSlider;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config extends Thread implements MinecraftInterface{

    private static final File mainFolder = new File(mc.runDirectory,"NoRatRealCurryMod");
    @Getter private static final File stolenSkinsPath = new File(mainFolder, "StolenSkins");
    private static final File modulePath = new File(mainFolder, "Module");
    private static final File hudPath = new File(mainFolder, "Hud");

    public static void load(){
        if(!mainFolder.exists()) mainFolder.mkdir();
        if(!stolenSkinsPath.exists()) stolenSkinsPath.mkdir();
        if(!modulePath.exists()) modulePath.mkdir();
        if(!hudPath.exists()) hudPath.mkdir();
        loadModules();
        loadHudElements();
    }

    private static void loadHudElements() {
        for (HudElement h : CurryMod.getInstance().getManagers().getHudManager().getHudElements()) {
            loadHudElement(h);
        }
    }

    private static void loadHudElement(HudElement hudElement) {
        try {
            Path path = Paths.get(hudPath.getAbsolutePath(), hudElement.getName() + ".json");
            if (!path.toFile().exists()) return;
            String rawJson = loadFile(path.toFile());
            JsonObject jsonObject = JsonParser.parseString(rawJson).getAsJsonObject();

            if (jsonObject.get("Enabled") != null) {
                if (jsonObject.get("Enabled").getAsBoolean()) {
                    hudElement.setEnabled(true);
                } else {
                    hudElement.setEnabled(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadModules() {
        for (Module module : CurryMod.getInstance().getManagers().getModuleManager().getModules()) {
            loadModule(module);
        }
    }

    private static void loadModule(Module module) {
        try {
            Path path = Paths.get(modulePath.getAbsolutePath(), module.getName() + ".json");
            if (!path.toFile().exists()) return;
            String rawJson = loadFile(path.toFile());
            JsonObject jsonObject = JsonParser.parseString(rawJson).getAsJsonObject();

            if (jsonObject.get("Enabled") != null) {
                if (jsonObject.get("Enabled").getAsBoolean()) {
                    module.setEnabled(true);
                } else {
                    module.setEnabled(false);
                }
            }
            if (jsonObject.get("Bind") != null) {
                module.setKey(jsonObject.get("Bind").getAsInt());
            }
            for (Option o : module.getOptions()) {
                if (o instanceof OptionMode op) {
                    if (jsonObject.get(o.getName()) != null) {
                        op.setIndex(jsonObject.get(o.getName()).getAsInt());
                    }
                }
                if (o instanceof OptionSlider op) {
                    if (jsonObject.get(o.getName()) != null) {
                        op.setValue(jsonObject.get(o.getName()).getAsFloat());
                    }
                }
                if (o instanceof OptionBoolean op) {
                    if (jsonObject.get(o.getName()) != null) {
                        op.setEnabled(jsonObject.get(o.getName()).getAsBoolean());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        save();
    }


    public void save(){
        saveModules();
        saveHudElements();

    }


    private static void saveModules() {
        for (Module module : CurryMod.getInstance().getManagers().getModuleManager().getModules()) {
            saveModule(module);
        }
    }

    private static void saveHudElements() {
        for (HudElement h : CurryMod.getInstance().getManagers().getHudManager().getHudElements()) {
            saveHudElement(h);
        }
    }

    private static void saveModule(Module module) {
        try {
            Path path = Paths.get(modulePath.getAbsolutePath(), module.getName() + ".json");
            createFile(path);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("Enabled", new JsonPrimitive(module.isEnabled()));
            jsonObject.add("Bind", new JsonPrimitive(module.getKey()));

            for (Option o : module.getOptions()) {
                if (o instanceof OptionBoolean op) {
                    jsonObject.add(o.getName(), new JsonPrimitive(op.isEnabled()));
                }
                if (o instanceof OptionSlider op) {
                    jsonObject.add(o.getName(), new JsonPrimitive(op.getValue()));

                }
                if (o instanceof OptionMode op) {
                    jsonObject.add(o.getName(), new JsonPrimitive(op.getIndex()));
                }
            }
            Gson gson = new Gson();
            Files.write(path, gson.toJson(JsonParser.parseString(jsonObject.toString())).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveHudElement(HudElement h) {
        try {
            Path path = Paths.get(hudPath.getAbsolutePath(), h.getName() + ".json");
            createFile(path);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("Enabled", new JsonPrimitive(h.isEnabled()));
            Gson gson = new Gson();
            Files.write(path, gson.toJson(JsonParser.parseString(jsonObject.toString())).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void createFile(Path path) {
        if (Files.exists(path)) new File(path.normalize().toString()).delete();
        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadFile(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file.getAbsolutePath());
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
