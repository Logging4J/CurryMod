package club.l4j.currymod.util;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.Option;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.feature.options.impl.OptionSlider;

import club.l4j.currymod.graphics.hudeditor.element.HudElement;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config extends Thread {

    private static final File mainFolder = new File("CurryMod");
    private static final String hacksPath = mainFolder.getAbsolutePath() + "/hacks";
    private static final String hudPath = mainFolder.getAbsolutePath() + "/hud";

    public static void load() {
        loadHacks();
        loadHudElements();
        loadPrefix();
    }

    private static void loadHacks() {
        for (Hack h : CurryMod.featureManager.hacks) {
            loadHack(h);
        }
    }

    private static void loadHudElements() {
        for (HudElement h : CurryMod.hudManager.getHudElements()) {
            loadHudElement(h);
        }
    }

    private static void loadPrefix() {
        try {
            Path path = Paths.get(mainFolder.getAbsolutePath(), "prefix.json");
            if (!path.toFile().exists()) return;
            String rawJson = loadFile(path.toFile());
            JsonObject obj = JsonParser.parseString(rawJson).getAsJsonObject();
            if (obj.get("Prefix") != null) {
                Command.setPrefix(obj.get("Prefix").getAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadHudElement(HudElement hudElement) {
        try {
            Path path = Paths.get(hudPath, hudElement.getName() + ".json");
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

    private static void loadHack(Hack hack) {
        try {
            Path path = Paths.get(hacksPath, hack.getName() + ".json");
            if (!path.toFile().exists()) return;
            String rawJson = loadFile(path.toFile());
            JsonObject jsonObject = JsonParser.parseString(rawJson).getAsJsonObject();

            if (jsonObject.get("Enabled") != null) {
                if (jsonObject.get("Enabled").getAsBoolean()) {
                    hack.setEnabled(true);
                } else {
                    hack.setEnabled(false);
                }
            }
            if (jsonObject.get("Bind") != null) {
                hack.setKey(jsonObject.get("Bind").getAsInt());
            }
            for (Option o : hack.getOptions()) {
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

    public static void save() {
        if ((!new File(hacksPath).exists() && !new File(hacksPath).mkdirs()) || (!new File(hudPath).exists() && !new File(hudPath).mkdirs())) {
            System.out.println("Failed to create modules folder");
        }
        saveHacks();
        saveHudElements();
        savePrefix();
    }

    private static void saveHacks() {
        for (Hack h : CurryMod.featureManager.hacks) {
            saveHack(h);
        }
    }

    private static void saveHudElements() {
        for (HudElement h : CurryMod.hudManager.getHudElements()) {
            saveHudElement(h);
        }
    }

    private static void savePrefix() {
        try {
            Path path = Paths.get(mainFolder.getAbsolutePath(), "prefix.json");
            createFile(path);
            JsonObject obj = new JsonObject();
            obj.add("Prefix", new JsonPrimitive(Command.getPrefix()));
            Gson gson = new Gson();
            Files.write(path, gson.toJson(JsonParser.parseString(obj.toString())).getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveHudElement(HudElement h) {
        try {
            Path path = Paths.get(hudPath, h.getName() + ".json");
            createFile(path);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("Enabled", new JsonPrimitive(h.isEnabled()));
            Gson gson = new Gson();
            Files.write(path, gson.toJson(JsonParser.parseString(jsonObject.toString())).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveHack(Hack h) {
        try {
            Path path = Paths.get(hacksPath, h.getName() + ".json");
            createFile(path);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("Enabled", new JsonPrimitive(h.isEnabled()));
            jsonObject.add("Bind", new JsonPrimitive(h.getKey()));

            for (Option o : h.getOptions()) {
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