package dev.logging4j.currymod.module.option.options;

import dev.logging4j.currymod.module.option.Option;

import java.awt.*;

//TODO
public class OptionColor extends Option<Color> {

    public OptionColor(String name, Color defaultValue) {
        super(name, defaultValue);
    }

    @Override
    protected boolean isValid(Color value) {
        return true;
    }
}
