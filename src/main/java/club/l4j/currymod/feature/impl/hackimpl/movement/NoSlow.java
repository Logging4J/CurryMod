package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionMode;

@Hack.Construct(name = "NoSlow", description = "no slow", category = Hack.Category.MOVEMENT)
public class NoSlow extends Hack {

    OptionMode mode = new OptionMode("Mode", "Vanilla","Vanilla");
    public NoSlow(){
        addOptions(mode);
    }


    @Override
    public String getContent() {
        return "["+mode.getMode()+"]";
    }
}
