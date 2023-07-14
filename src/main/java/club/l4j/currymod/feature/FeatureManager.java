package club.l4j.currymod.feature;

import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.impl.commandimpl.*;
import club.l4j.currymod.feature.impl.hackimpl.client.*;
import club.l4j.currymod.feature.impl.hackimpl.combat.*;
import club.l4j.currymod.feature.impl.hackimpl.exploit.*;
import club.l4j.currymod.feature.impl.hackimpl.misc.*;
import club.l4j.currymod.feature.impl.hackimpl.movement.*;
import club.l4j.currymod.feature.impl.hackimpl.visual.*;
import club.l4j.currymod.util.IGlobals;

import java.util.ArrayList;
import java.util.List;

public class FeatureManager implements IGlobals {

    public List<Hack> hacks = new ArrayList<>();
    public List<Command> commands = new ArrayList<>();

    public FeatureManager() {
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

        //Commands
        addCommand(new VClip());
        addCommand(new SilentLeave());
        addCommand(new Help());
        addCommand(new FakePlayer());
        addCommand(new GameModes());
        addCommand(new Emoji());
        addCommand(new NBT());
        addCommand(new Prefix());
    }

    public void runCommand(String args) {
        boolean found = false;
        String[] split = args.split(" ");
        String startCommand = split[0];
        String arguments = args.substring(startCommand.length()).trim();
        for (Command command : commands) {
            for (String alias : command.getAlias()) {
                if (startCommand.equals(command.getPrefix() + alias)) {
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
