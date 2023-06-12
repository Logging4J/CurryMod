package club.l4j.currymod.feature;

import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.impl.commandimpl.*;
import club.l4j.currymod.feature.impl.hackimpl.client.ClickGuiMod;
import club.l4j.currymod.feature.impl.hackimpl.client.HUD;
import club.l4j.currymod.feature.impl.hackimpl.combat.*;
import club.l4j.currymod.feature.impl.hackimpl.exploit.*;
import club.l4j.currymod.feature.impl.hackimpl.misc.AutoLog;
import club.l4j.currymod.feature.impl.hackimpl.misc.ChatEdits;
import club.l4j.currymod.feature.impl.hackimpl.misc.FastUse;
import club.l4j.currymod.feature.impl.hackimpl.movement.*;
import club.l4j.currymod.feature.impl.hackimpl.visual.CameraClip;
import club.l4j.currymod.feature.impl.hackimpl.visual.FullBright;
import club.l4j.currymod.feature.impl.hackimpl.visual.NoRender;

import club.l4j.currymod.util.IGlobals;

import java.util.ArrayList;
import java.util.List;

public class FeatureManager implements IGlobals {

    public List<Hack> hacks = new ArrayList<>();
    public List<Command> commands = new ArrayList<>();
    public String prefix = "@";

    public FeatureManager() {
        //Hacks
        addHack(new AutoLog());
        addHack(new BoatExecutor());
        addHack(new CameraClip());
        addHack(new ClickGuiMod());
        addHack(new FastUse());
        addHack(new Flight());
        addHack(new FullBright());
        addHack(new HUD());
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

        //Commands
        addCommand(new VClip());
        addCommand(new SilentLeave());
        addCommand(new Lenny());
        addCommand(new Shrug());
        addCommand(new Help());
        addCommand(new FakePlayer());
    }

    public void runCommand(String args) {
        boolean found = false;
        String[] split = args.split(" ");
        String startCommand = split[0];
        String arguments = args.substring(startCommand.length()).trim();
        for (Command command : commands) {
            for (String alias : command.getAlias()) {
                if (startCommand.equals(prefix + alias)) {
                    command.onTrigger(arguments);
                    found = true;
                }
            }
        }
        if (!found) {
            sendMsg("Unknown command");
        }
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

    private void addCommand(Command command) {
        commands.add(command);
    }

}
