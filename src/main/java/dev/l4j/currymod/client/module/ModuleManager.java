package dev.l4j.currymod.client.module;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.modules.client.ClickGUI;
import dev.l4j.currymod.client.module.modules.client.HudEditor;
import dev.l4j.currymod.client.module.modules.combat.Offhand;
import dev.l4j.currymod.client.module.modules.fun.BasketBallPeople;
import dev.l4j.currymod.client.module.modules.fun.Jew;
import dev.l4j.currymod.client.module.modules.fun.NaziClouds;
import dev.l4j.currymod.client.module.modules.fun.TudouSky;
import dev.l4j.currymod.client.module.modules.misc.AutoRespawn;
import dev.l4j.currymod.client.module.modules.movement.EntityControl;
import dev.l4j.currymod.client.module.modules.movement.Flight;
import dev.l4j.currymod.client.module.modules.movement.NoFall;
import dev.l4j.currymod.client.module.modules.visual.*;
import dev.l4j.currymod.client.module.option.options.OptionKeybind;
import dev.l4j.currymod.listener.IKeyListener;
import dev.l4j.currymod.util.MinecraftInterface;
import lombok.Getter;
import lombok.SneakyThrows;
import org.lwjgl.glfw.GLFW;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Getter
public class ModuleManager implements IKeyListener, MinecraftInterface {
    private final List<Module> modules;

    public ModuleManager() {
        modules = new CopyOnWriteArrayList<>();

        add(Jew.class);
        add(NaziClouds.class);
        add(TudouSky.class);
        add(HudEditor.class);
        add(ClickGUI.class);
        add(Brightness.class);
        add(Flight.class);
        add(AutoRespawn.class);
        add(Offhand.class);
        add(KillEffects.class);
        add(ExtraTab.class);
        add(ToolTips.class);
        add(SwingSpeed.class);
        add(NoFall.class);
        add(EntityControl.class);
        add(BasketBallPeople.class);

        DietrichEvents2.global().subscribe(KeyEvent.ID, this);
    }

    @SneakyThrows
    private void add(Class<? extends Module> moduleClass) {
        modules.add(moduleClass.getConstructor().newInstance());
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(Class<T> module) {
        return (T) this.modules.stream()
                .filter(mod -> mod.getClass().equals(module))
                .findFirst()
                .orElse(null);
    }

    public Module getModule(String name) {
        return this.modules.stream()
                .filter(module -> module.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Module> getEnabledModules() {
        return modules.stream()
                .sorted(Comparator.comparing(Module::getName))
                .filter(Module::isEnabled)
                .collect(Collectors.toList());
    }

    public List<Module> getModulesInCategory(Module.Category category) {
        return modules.stream()
                .sorted(Comparator.comparing(Module::getName))
                .filter(module -> module.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public void onKey(KeyEvent event) {
        if (event.getAction() == GLFW.GLFW_PRESS && mc.currentScreen == null) {
            modules.forEach(module -> {
                OptionKeybind keybind = module.getKeybind();
                if (keybind.getValue().getCode() == event.getKey()) {
                    module.toggle();
                }
            });
        }
    }
}
