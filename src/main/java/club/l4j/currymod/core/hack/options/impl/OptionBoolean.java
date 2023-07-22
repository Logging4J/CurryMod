package club.l4j.currymod.core.hack.options.impl;

import club.l4j.currymod.core.hack.options.Option;
import lombok.Getter;
import lombok.Setter;

public class OptionBoolean extends Option {

    @Getter @Setter
    private boolean enabled;

    public OptionBoolean(String name, boolean defVal){
        super(name);
        enabled = defVal;
    }

    public void toggle(){
        enabled = !enabled;
    }
}
