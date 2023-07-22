package club.l4j.currymod.core.manager;

import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.impl.hacks.client.*;
import club.l4j.currymod.impl.hacks.combat.*;
import club.l4j.currymod.impl.hacks.exploit.*;
import club.l4j.currymod.impl.hacks.misc.*;
import club.l4j.currymod.impl.hacks.movement.*;
import club.l4j.currymod.impl.hacks.visual.*;
import club.l4j.currymod.core.util.IGlobals;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class HackManager implements IGlobals {

    @Getter
    private List<Hack> hacks = new ArrayList<>();

    public HackManager() {
        //Hacks
        addHack(new AutoLog());
        addHack(new BoatExecutor());
        addHack(new CameraClip());
        addHack(new ClickGuiMod());
        addHack(new FastUse());
        addHack(new Flight());
        addHack(new FullBright());
        addHack(new NoFall());
        addHack(new NoRender());
        addHack(new NoSlow());
        addHack(new Speed());
        addHack(new Sprint());
        addHack(new Velocity());
        addHack(new Timer());
        addHack(new ChatEdits());
        addHack(new Aura());
        addHack(new Parkour());
        addHack(new AutoWalk());
        addHack(new HitBoxDesync());
        addHack(new ConsoleSpammer());
        addHack(new Step());
        addHack(new Colors());
        addHack(new MachineGun());
        addHack(new FreeLook());
        addHack(new HudEditor());
        addHack(new MiddleClick());
        addHack(new Atmosphere());
        addHack(new CrystalTrigBot());
        addHack(new GuiBackground());
        addHack(new AntiReGear());
        addHack(new AutoTool());
        addHack(new Xray());
        addHack(new XCarry());
    }

    public List<Hack> getEnabledHackFeatures() {
        List<Hack> enabled = new ArrayList<>();
        for (Hack hack : hacks) {
            if (hack.isEnabled()) {
                enabled.add(hack);
            }
        }
        return enabled;
    }


    public Hack getHack(String name) {
        Hack h = null;
        for (Hack hack : hacks) {
            if (name.equalsIgnoreCase(hack.getName())) {
                h = hack;
            }
        }
        return h;
    }

    public List<Hack> getHackFeaturesInCategory(Hack.Category category) {
        List<Hack> catHackFeatures = new ArrayList<>();
        for (Hack hack : hacks) {
            if (hack.getCategory() == category) {
                catHackFeatures.add(hack);
            }
        }
        return catHackFeatures;
    }

    private void addHack(Hack hack) {
        hacks.add(hack);
    }

}
