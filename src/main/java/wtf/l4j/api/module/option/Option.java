package wtf.l4j.api.module.option;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Option {

    private String name;

    @Setter private boolean visible = true;

    public Option(String name) {
        this.name = name;
    }

}
