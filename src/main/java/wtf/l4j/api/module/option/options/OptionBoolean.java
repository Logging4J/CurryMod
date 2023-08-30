package wtf.l4j.api.module.option.options;

import lombok.Getter;
import lombok.Setter;

import wtf.l4j.api.module.option.Option;

@Getter @Setter
public class OptionBoolean extends Option {

    private boolean enabled;

    public OptionBoolean(String name, boolean defVal){
        super(name);
        enabled = defVal;
    }

    public void toggle(){
        enabled = !enabled;
    }

}
