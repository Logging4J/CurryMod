package club.l4j.currymod.impl.hacks.visual;

import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionMode;

@Hack.Construct(name = "GuiBackground(WIP)",description = "Changes the background of the gui", category = Hack.Category.VISUAL)
public class GuiBackground extends Hack {

    public OptionMode mode = new OptionMode("Mode", "Remove", "Remove", "Blur");

    public GuiBackground() {
        addOptions(mode);
    }

}
