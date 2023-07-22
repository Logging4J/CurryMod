package club.l4j.currymod.impl.hacks.movement;

import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionMode;

@Hack.Construct(name = "NoSlow", description = "no slow", category = Hack.Category.MOVEMENT)
public class NoSlow extends Hack {

    public OptionMode mode = new OptionMode("Mode", "Vanilla","Vanilla");

    public NoSlow(){
        addOptions(mode);
    }

    @Override
    public String getContent() {
        return "["+mode.getMode()+"]";
    }
}
