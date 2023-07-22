package club.l4j.currymod.core.hack.options.impl;

import club.l4j.currymod.core.hack.options.Option;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class OptionMode extends Option {

    @Getter
    private String mode;

    @Getter
    private List<String> modes;

    @Getter
    private int index;

    public OptionMode(String name, String defVal, String... modes) {
        super(name);
        this.modes = Arrays.asList(modes);
        mode = defVal;
        index = this.modes.indexOf(defVal);
    }

    public void setMode(String mode) {
        this.mode = mode;
        index = modes.indexOf(mode);
    }

    public void setIndex(int index) {
        this.index = index;
        mode = modes.get(index);
    }

    public void cycle(){
        if(index < modes.size() -1){
            index++;
            mode = modes.get(index);
        } else if (index >= modes.size() - 1) {
            index = 0;
            mode = modes.get(0);
        }
    }

    public boolean isMode(String mode){
        return this.mode == mode;
    }
}
