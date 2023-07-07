package club.l4j.currymod.feature.impl.hackimpl.visual;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionMode;

@Hack.Construct(name = "GuiBackground(WIP)",description = "Changes the background of the gui", category = Hack.Category.VISUAL)
public class GuiBackground extends Hack {

    public OptionMode mode = new OptionMode("Mode", "Remove", "Remove", "Blur");

    public GuiBackground() {
        addOptions(mode);
    }

}
