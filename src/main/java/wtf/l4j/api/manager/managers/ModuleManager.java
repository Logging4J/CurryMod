package wtf.l4j.api.manager.managers;

import lombok.Getter;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.impl.modules.client.*;
import wtf.l4j.impl.modules.combat.AutoCrystal;
import wtf.l4j.impl.modules.combat.KillAura;
import wtf.l4j.impl.modules.combat.TotemPopCounter;
import wtf.l4j.impl.modules.combat.Velocity;
import wtf.l4j.impl.modules.crash.CompletionCrash;
import wtf.l4j.impl.modules.crash.IntChatCrash;
import wtf.l4j.impl.modules.exploit.*;
import wtf.l4j.impl.modules.misc.*;
import wtf.l4j.impl.modules.movement.*;
import wtf.l4j.impl.modules.visual.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class ModuleManager {

    private List<Module> modules;

    public void init() {
        modules = new LinkedList<>();

        modules.add(new ClickGui());
        modules.add(new Colors());
        modules.add(new HudEditor());
        modules.add(new AutoCrystal());
        modules.add(new KillAura());
        modules.add(new Velocity());
        modules.add(new TotemPopCounter());
        modules.add(new ConsoleSpammer());
        modules.add(new AutoJQQ());
        modules.add(new AutoTool());
        modules.add(new AutoWalk());
        modules.add(new Blink());
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
        modules.add(new EntityControl());
        modules.add(new CapesModule());
        modules.add(new FakePlayer());
        modules.add(new VisualRange());
        modules.add(new FastStop());
        modules.add(new ViewLock());
        modules.add(new ViewModel());
        modules.add(new PacketCanceller());
        modules.add(new HitboxDesync());
        modules.add(new FreeCam());
        modules.add(new HighJump());
        modules.add(new InventoryMove());
        modules.add(new DiscordRPC());
        modules.add(new NameProtect());
    }

    public Module getModule(Class<?> module) {
        return this.modules.stream()
                .filter(mod -> mod.getClass().equals(module))
                .findFirst()
                .orElse(null);
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
