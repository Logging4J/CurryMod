package club.l4j.currymod.feature.options;

import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.feature.options.impl.OptionSlider;

public class Option {

    private String name;
    private boolean visible = true;

    public Option(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isBoolean(Option option) {
        if(option instanceof OptionBoolean){
            return true;
        }
        return false;
    }

    public boolean isNumber(Option option) {
        if(option instanceof OptionSlider){
            return true;
        }
        return false;
    }

    public boolean isMode(Option option) {
        if(option instanceof OptionMode){
            return true;
        }
        return false;
    }
}
