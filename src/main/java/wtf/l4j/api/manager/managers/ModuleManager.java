package wtf.l4j.api.manager.managers;

import lombok.Getter;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.impl.modules.client.ClickGui;
import wtf.l4j.impl.modules.client.Colors;
import wtf.l4j.impl.modules.client.HudEditor;
import wtf.l4j.impl.modules.combat.CurryAura;
import wtf.l4j.impl.modules.combat.KillAura;
import wtf.l4j.impl.modules.combat.PlayerModifier;
import wtf.l4j.impl.modules.crash.IntChatCrash;
import wtf.l4j.impl.modules.exploit.BoatExecute;
import wtf.l4j.impl.modules.exploit.ConsoleSpammer;
import wtf.l4j.impl.modules.exploit.XCarry;
import wtf.l4j.impl.modules.misc.*;
import wtf.l4j.impl.modules.movement.*;
import wtf.l4j.impl.modules.visual.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class ModuleManager {

    private List<Module> modules;

    public void init() {
        modules = new ArrayList<>();
        modules.add(new ClickGui());
        modules.add(new Colors());
        modules.add(new HudEditor());
        modules.add(new CurryAura());
        modules.add(new KillAura());
        modules.add(new PlayerModifier());
        modules.add(new IntChatCrash());
        modules.add(new BoatExecute());
        modules.add(new ConsoleSpammer());
        modules.add(new AutoJQQ());
        modules.add(new AutoTool());
        modules.add(new AutoWalk());
        modules.add(new Flight());
        modules.add(new NoFall());
        modules.add(new NoSlow());
        modules.add(new Sprint());
        modules.add(new Step());
        modules.add(new Timer());
        modules.add(new FreeLook());
        modules.add(new FullBright());
        modules.add(new NoRender());
        modules.add(new WorldTime());
        modules.add(new Xray());
        modules.add(new ExtraTab());
        modules.add(new CameraClip());
        modules.add(new ChatSuffix());
        modules.add(new GreenText());
        modules.add(new AntiLevitation());
        modules.add(new AutoRespawn());
        modules.add(new Spammer());
        modules.add(new XCarry());
        modules.add(new StorageESP());
    }

    public Optional<Module> getModule(Class<?> module) {
        return Optional.ofNullable(this.modules.stream()
                .filter(mod -> mod.getClass().equals(module))
                .findFirst()
                .orElse(null)
        );
    }

    public Optional<List<Module>> getModulesInCategory(Category category) {
        return Optional.of(this.modules.stream()
                .filter(mod -> mod.getCat().equals(category))
                .collect(Collectors.toList())
        );
    }

    public Optional<List<Module>> getEnabledModules() {
        return Optional.of(this.modules.stream()
                .filter(Module::isEnabled)
                .collect(Collectors.toList()));
    }

}
