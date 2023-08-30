package wtf.l4j.api.module.option.options;

import lombok.Getter;
import wtf.l4j.api.module.option.Option;

import java.util.Arrays;
import java.util.List;

@Getter
public class OptionMode extends Option {

    private String mode;
    private List<String> modes;
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
