package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionMode;

@Hack.Construct(name = "Surround", description = "HvH Moment", category = Hack.Category.COMBAT)
public class Surround extends Hack {

    OptionMode mode = new OptionMode("Mode", "Obsidian", "Obsidian", "Bedrock");

    public Surround() {
        addOptions(mode);
    }

    int obiSlot;
    int oldSlot;


}
